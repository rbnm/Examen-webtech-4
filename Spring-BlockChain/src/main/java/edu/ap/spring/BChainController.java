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

import edu.ap.spring.service.Block;
import edu.ap.spring.service.BlockChain;
import edu.ap.spring.service.Wallet;
import edu.ap.spring.transaction.Transaction;

@Controller
public class BChainController {

    @Autowired
	private BlockChain bChain;
	@Autowired
	private Wallet coinbase, walletA, walletB;
	private Transaction genesisTransaction;



    @GetMapping("/transaction")
    public String getForm() {
        return "transaction";
    }

    @PostMapping("/transaction")
    public String sendFunds(@RequestParam("from") Wallet from, @RequestParam("to") Wallet to,
    @RequestParam("amount") Float amount) {
        Block block = new Block();
		block.setPreviousHash(bChain.getLastHash());
        Wallet sender = from;
        Wallet ontvanter = to;
		try {
			block.addTransaction(sender.sendFunds(ontvanter.getPublicKey(), amount), bChain);
		} 
		catch(Exception e) {}
		
		bChain.addBlock(block);
        return "redirect:/transaction";
    }

    @GetMapping(value="/balance/{wallet}")
    public String getBalance(@PathVariable("wallet") Wallet wallet, Model model) {
        Wallet wlt = (Wallet)wallet;
        float balance = wlt.getBalance();
        model.addAttribute("balance", balance);
        return "balance";
    }
    
}