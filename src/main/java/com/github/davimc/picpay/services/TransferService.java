package com.github.davimc.picpay.services;

import com.github.davimc.picpay.DTO.TransferDTO;
import com.github.davimc.picpay.DTO.TransferNewDTO;
import com.github.davimc.picpay.entities.Transfer;
import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.entities.Wallet;
import com.github.davimc.picpay.repositories.TransferRepository;
import com.github.davimc.picpay.services.exceptions.NotAuthorizedException;
import com.github.davimc.picpay.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
public class TransferService {
    private final TransferRepository repository;
    private final WalletService walletService;
    private final RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;

    public TransferService(TransferRepository repository, WalletService walletService, RestTemplate restTemplate) {
        this.repository = repository;
        this.walletService = walletService;
        this.restTemplate = restTemplate;
    }
    @Transactional(readOnly = true)
    protected Transfer find (Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Transfer.class));
    }
    @Transactional(readOnly = true)
    public TransferDTO findById (Long id) {
        Transfer obj = find(id);
        return new TransferDTO(obj);
    }
    public Page<TransferDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(TransferDTO::new);
    }

    public TransferDTO insert(User user, TransferNewDTO dto) {
        Wallet w1 = walletService.findByUser(user);
        Wallet w2 = walletService.find(dto.receiver());

        try {
            authorizationService();
            walletService.transfer(w1, w2, dto.amount());
            Transfer obj = repository.save(new Transfer(null, dto.amount(), w1, w2));

            notificationService.notify(user, w2.getUser(), "Transfer");
            return new TransferDTO(obj);
        }catch (NullPointerException e) {
            throw new NotAuthorizedException("Não autorizado pelo servidor");
        }
    }

    private void authorizationService() throws NullPointerException{

            var response = restTemplate.getForEntity("http://localhost:8081/authorization/transfer", Map.class);

            String result = Objects.requireNonNull(response.getBody()).get("message").toString();
            if (result.equalsIgnoreCase("Não Autorizado"))
                throw new NotAuthorizedException(result + " pelo servidor");

    }

    /*private boolean authorizeTransfer(User user, BigDecimal value) {
        ResponseEntity<Map> authorizeTransfer = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",)

    }*/
}
