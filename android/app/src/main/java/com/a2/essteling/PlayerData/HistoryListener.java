package com.a2.essteling.PlayerData;

import com.a2.essteling.PlayerData.History;

import java.util.LinkedList;

public interface HistoryListener {
    void onHistoryReceived(LinkedList<History> histories);
}
