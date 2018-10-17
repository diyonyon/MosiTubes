/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mositubes;

import java.util.Random;

/**
 *
 * @author Arshyan Hadian Aziz
 */
public class MosiTubes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r = new Random();
        Graph g =  new Graph(7);
        g.addEdge(1, 2, r.nextInt(6) + 3);
        g.addEdge(1, 3, r.nextInt(6) + 3);
        g.addEdge(1, 4, r.nextInt(6) + 3);
        g.addEdge(2, 4, r.nextInt(6) + 3);
        g.addEdge(2, 5, r.nextInt(6) + 3);
        g.addEdge(2, 7, r.nextInt(6) + 3);
        g.addEdge(3, 4, r.nextInt(6) + 3);
        g.addEdge(3, 6, r.nextInt(6) + 3);
        g.addEdge(4, 6, r.nextInt(6) + 3);
        g.addEdge(5, 7, r.nextInt(6) + 3);
        g.addEdge(6, 7, r.nextInt(6) + 3);
        
        /*ArrayList<ArrayList<Integer>> routes = new ArrayList<>();
        routes = g.evaluateRoutes();
        for (int n = 0; n < routes.size(); n++) {
            for (int m = 0; m < routes.get(n).size(); m++) {
                System.out.print(routes.get(n).get(m) + " ");
            }
            System.out.println();
        }
        
        System.out.println();
        g.edgeUsage(routes);
        
        ArrayList<Edge> edgeList = g.getEdgeList();
        
        for (int n = 0; n < edgeList.size(); n++) {
            System.out.print(n + " ");
            for (int m = 0; m < edgeList.get(n).getUsedByRoutes().size(); m++) {
                System.out.print(edgeList.get(n).getUsedByRoutes().get(m) + " ");
            }
            System.out.println();
        }*/
        g.setRouteList();
        for (int n = 0; n < g.getEdgeList().size(); n++) {
            System.out.println("Edge Number " + n + 
                               " (Starting Node : " + g.getEdgeList().get(n).getStartingNode() +
                               ", Destination Node : " + g.getEdgeList().get(n).getDestinationNode() +
                               "), Edge Cost : " + g.getEdgeCost(n));
        }
        
        System.out.println();
        
        /*ArrayList<Integer> alInt = g.findSingleUseEdges();
        for (int n = 0; n < alInt.size(); n++) {
            System.out.print(n + ". Edge Number : " + alInt.get(n) + ". Used by Route number : ");
            System.out.println(g.getEdgeList().get(alInt.get(n)).getUsedByRoutes().get(0));
            
        }*/
        for (int n = 0; n < g.getRouteList().size(); n++) {
            System.out.print("Route " + n + " Tranverses nodes ");
            for (int m = 0; m < g.getRouteList().get(n).size(); m++) {
                if (m < g.getRouteList().get(n).size() - 1) {
                    System.out.print(g.getRouteList().get(n).get(m) + " ");
                } else {
                    System.out.println(", With the total cost of : " + g.getRouteList().get(n).get(m));
                }
            }
        }
        System.out.println();
        System.out.println("Maximum Cost : " + g.getMaxCost());
        System.out.println();
        System.out.println("Toll Gates Placed at the Following Edges : ");
        g.setTollGates();
    }
}
