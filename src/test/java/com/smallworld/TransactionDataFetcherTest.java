package com.smallworld;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.data.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionDataFetcherTest {

    private static TransactionDataFetcher transactionDataFetcher;

    @BeforeAll
    public static void setUp() throws Exception {

        String jsonContent = loadResourceAsString("transactions.json");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Transaction> transactions = objectMapper.readValue(jsonContent, new TypeReference<>() {});

        transactionDataFetcher = new TransactionDataFetcher();
        transactionDataFetcher.setTransactions(transactions);
    }

    private static String loadResourceAsString(String resourceName) throws Exception {
        ClassLoader classLoader = TransactionDataFetcherTest.class.getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append(System.lineSeparator());
        }
        return content.toString();
    }

    @Test
    public void testGetTotalTransactionAmount() {
        double totalTransactionAmount = transactionDataFetcher.getTotalTransactionAmount();
        assertEquals(2889.17, totalTransactionAmount);
    }

    @Test
    public void testGetTotalTransactionAmountSentBy() {
        double totalAmountSentBy = transactionDataFetcher.getTotalTransactionAmountSentBy("Grace Burgess");
        assertEquals(666.0, totalAmountSentBy);
    }

    @Test
    public void testGetMaxTransactionAmount() {
        double totalAmountSentBy = transactionDataFetcher.getMaxTransactionAmount();
        assertEquals(985.0, totalAmountSentBy);
    }

    @Test
    public void testCountUniqueClients() {
        long uniqueClientsCount = transactionDataFetcher.countUniqueClients();
        assertEquals(14, uniqueClientsCount);
    }

    @Test
    public void testHasOpenComplianceIssues() {
        boolean hasOpenCompliance = transactionDataFetcher.hasOpenComplianceIssues("Arthur Shelby");
        assertTrue(hasOpenCompliance);

        hasOpenCompliance = transactionDataFetcher.hasOpenComplianceIssues("Aunt Polly");
        assertFalse(hasOpenCompliance);
    }

    @Test
    public void testGetTransactionsByBeneficiaryName() {
        Map<String, List<Transaction>> res = transactionDataFetcher.getTransactionsByBeneficiaryName();
        assertEquals(10, res.size());
    }

    @Test
    public void testGetUnsolvedIssueIds() {
        Set<Integer> unresolveIssueIds = transactionDataFetcher.getUnsolvedIssueIds();
        assertEquals(3, unresolveIssueIds.size());
    }

    @Test
    public void testGetAllSolvedIssueMessages() {
        List<String> messages = transactionDataFetcher.getAllSolvedIssueMessages();
        assertEquals(8, messages.size());
    }

    @Test
    public void testGetTop3TransactionsByAmount() {
        List<Transaction> transactions = transactionDataFetcher.getTop3TransactionsByAmount();
        assertEquals(3, transactions.size());
    }

    @Test
    public void testGetTopSender() {
        Optional<String> topSender = transactionDataFetcher.getTopSender();
        assertTrue(topSender.isPresent());
    }

}
