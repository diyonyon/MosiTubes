/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mositubes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Arshyan Hadian Aziz
 */
class Graph {
    private int jumlahNode = 0;
    private ArrayList<Edge> edgeList = new ArrayList<>();
    private ArrayList<Integer> singleUseRoutes = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> routeList;
    
    public Graph(int jumlahNode) {
        this.jumlahNode = jumlahNode;
    }
    
    public void addEdge(int startingNode, int destinationNode, int cost) {
        edgeList.add(new Edge(startingNode, destinationNode, cost));
    }
    
    public int getStartingNodeOfEdge(int edgeNumber) {
        Edge e = edgeList.get(edgeNumber);
        return e.getStartingNode();
    }
    
    public int getDestinationNodeOfEdge(int edgeNumber) {
        Edge e = edgeList.get(edgeNumber);
        return e.getDestinationNode();
    }
    
    public int getEdgeCost(int edgeNumber) {
        Edge e = edgeList.get(edgeNumber);
        return e.getCost();
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void setRouteList() {
        this.routeList = evaluateRoutes();
    }

    public ArrayList<ArrayList<Integer>> getRouteList() {
        return routeList;
    }
    
    public ArrayList<ArrayList<Integer>> evaluateRoutes() {
        Queue<ArrayList<Integer>> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> toReturn = new ArrayList<>();
        // Menambahkan edge dari node pertama ke queue
        for (int n = 0; n < edgeList.size(); n++) {
            if (getStartingNodeOfEdge(n) == 1) {
                ArrayList<Integer> firstEdges = new ArrayList<>();
                firstEdges.add(1);
                firstEdges.add(getDestinationNodeOfEdge(n));
                firstEdges.add(getEdgeCost(n));
                q.add(firstEdges);
            }
        }
        
        while (q.peek() != null) {
            ArrayList<Integer> routeBuilder = q.remove();
            // Mendapatkan cost dari rute yang sedang dievaluasi
            int cost = routeBuilder.get(routeBuilder.size() - 1);
            
            routeBuilder.remove(routeBuilder.size() - 1);
            for (int n = 0; n < edgeList.size(); n++) {
                if (getStartingNodeOfEdge(n) == routeBuilder.get(routeBuilder.size() - 1)) {
                    ArrayList<Integer> builderSnap = new ArrayList<>(routeBuilder);
                    routeBuilder.add(getDestinationNodeOfEdge(n));
                    routeBuilder.add(getEdgeCost(n) + cost);
                    
                    if (getDestinationNodeOfEdge(n) == 7) {
                        toReturn.add(routeBuilder);
                    } else {
                        q.add(routeBuilder);
                    }
                    
                    routeBuilder = new ArrayList<>(builderSnap);
                }
            }
        }

        return toReturn;
    }
    
    public void edgeUsage(ArrayList<ArrayList<Integer>> routes) {
        for (int n = 0; n < routes.size(); n++) {
            for (int m = 0; m < routes.get(n).size() - 2; m++) {
                boolean found = false;
                int i = 0;
                while(!found) {
                    if ((routes.get(n).get(m) == getStartingNodeOfEdge(i)) &&
                        (routes.get(n).get(m + 1) == getDestinationNodeOfEdge(i))) {
                        edgeList.get(i).addRouteUse(n);
                        found = true;
                    } else {
                        i++;
                    }
                }
            }
        }
    } 
    
    public ArrayList<Integer> findSingleUseEdges() {
        edgeUsage(routeList);
        ArrayList<Integer> toReturn = new ArrayList<>();
        for (int n = 0; n < edgeList.size(); n++) {
            ArrayList<Integer> howManyRoutes = edgeList.get(n).getUsedByRoutes();
            if (howManyRoutes.size() == 1) {
                toReturn.add(n);
            }
        }
        
        return toReturn;
    }
    
    public int getMaxCost() {
        int toReturn = 0;
        for (int n = 0; n < routeList.size(); n++) {
            int tmp = routeList.get(n).get(routeList.get(n).size() - 1);
            if (tmp > toReturn) {
                toReturn = tmp;
            } 
        }
        
        return toReturn;
    }
    
    public void setTollGates() {
        int maxCost = getMaxCost();
        ArrayList<Integer> singleUseEdge = findSingleUseEdges();
        
        for (int n = 0; n < routeList.size(); n++) {
            if (routeList.get(n).get(routeList.get(n).size() - 1) != maxCost) {
                int tollCost = maxCost - (routeList.get(n).get(routeList.get(n).size() - 1));
                //System.out.println("Route " + n + " : " + tollCost);
                boolean found = false;
                int i = 0;
                while (!found) {
                    if (getEdgeList().get(singleUseEdge.get(i)).getUsedByRoutes().get(0) == n) {
                        found = true;
                        System.out.println("Edge number : " + singleUseEdge.get(i) +
                                           " (Start Node : " + getEdgeList().get(singleUseEdge.get(i)).getStartingNode() +
                                           ", Destination Node : " + getEdgeList().get(singleUseEdge.get(i)).getDestinationNode() +
                                           "), Toll Cost : " + tollCost);
                    } else {
                        i++;
                    }
                }
            }
        }
    }
}
