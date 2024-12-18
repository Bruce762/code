package org.example;

/**
 * 每個州的投票資訊(州名字、選舉人票數、一號候選人得票數、二號候選人得票數)
 */
@lombok.Data
public class ticketData {
    String state;
    String ElectoralVotes;
    String candidate1Vote;
    String candidate2Vote;
}
