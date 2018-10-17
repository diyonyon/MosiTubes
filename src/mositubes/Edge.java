/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mositubes;

import java.util.ArrayList;

/**
 *
 * @author Arshyan Hadian Aziz
 */
class Edge {
    private int startingNode;
    private int destinationNode;
    private int cost;
    private ArrayList<Integer> usedByRoutes;
    
    public Edge(int startingNode, int destinationNode, int cost) {
        this.startingNode = startingNode;
        this.destinationNode = destinationNode;
        this.cost = cost;
        this.usedByRoutes = new ArrayList<>();
    }

    public int getStartingNode() {
        return startingNode;
    }

    public int getDestinationNode() {
        return destinationNode;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<Integer> getUsedByRoutes() {
        return usedByRoutes;
    }
    
    
    public void addRouteUse(int routeNumber) {
        usedByRoutes.add(routeNumber);
    }
}
