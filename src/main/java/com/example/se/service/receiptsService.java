package com.example.se.service;
import com.example.se.model.receipts;

import java.util.List;

public interface receiptsService {
    /** Find all receipts
     * /
     * @return all receipts
     */
    List<receipts> findAllreceipts();

    /** Save method
     * @param receipts
     * @return
     * receipts object
     */
    receipts save(receipts receipts);
    /** Find receipts by Id
     * @param receiptId
     * @return
     * receipts object
     */
    receipts findReceiptsbyId(int receiptId);

    receipts findByReceiptsId(int receiptId);

    /**
     * Delete method
     * @param receiptId
     */
    void deletebyId(int receiptId);
}
