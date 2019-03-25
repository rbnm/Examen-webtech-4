package edu.ap.spring;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.ap.spring.service.Wallet;

@Controller
public class BChainController {

    @GetMapping("/transaction")
    public String getForm() {
        return "sendFunds";
    }

    @PostMapping("/transaction")
    public String sendFunds(@RequestParam("from") String from, @RequestParam("to") String to,
    @RequestParam("amount") Float amount) {
        Wallet senderWallet = bChain.getWalletFromKey(from);
        Wallet receiverWallet = bChain.getWalletFromKey(to);
        try{
        bChain.block1.addTransaction(senderWallet.sendFunds(receiverWallet.publicKey, amount), bChain.bChain);
        }catch(Exception e){}
        bChain.bChain.addBlock(bChain.block1);
        return "redirect:/";
    }
}