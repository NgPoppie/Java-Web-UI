package com.example.solana_chat_ui;

import org.bitcoinj.core.Base58;
import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.core.Transaction;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.ConfirmationStatus;

public class SolanaService {
    private final RpcClient client;
    private final PublicKey programId;

    public SolanaService(String rpcUrl, String programId) {
        this.client = new RpcClient(rpcUrl);
        this.programId = new PublicKey(programId);
    }

    public void sendMessage(String userPubKey, String messageContent) throws RpcException {
        PublicKey sender = new PublicKey(userPubKey);
        Transaction transaction = new Transaction();

        // Add your message sending logic here, e.g., creating an instruction to send the message

        client.getApi().sendTransaction(transaction, sender, ConfirmationStatus.CONFIRMED);
    }

    // Add other methods as needed, e.g., fetching messages
}
