package com.a2.essteling.ScoreBoard;

import java.util.LinkedList;

public interface HistoryListener {
    void onHistoryReceived(LinkedList<History> histories);
}
