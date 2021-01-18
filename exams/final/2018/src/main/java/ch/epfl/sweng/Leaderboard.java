package ch.epfl.sweng;

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CANNOT edit or remove the signatures of the existing methods/constructors.
// You CANNOT add new constructors.
// You CAN change the implementation of the methods/constructors.
// You CAN add new methods.
// You CAN add interface implementations.
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

import javafx.geometry.Pos;

import java.util.*;

/**
 * A leaderboard that counts points.
 * The points are tallied as follows:
 * - Posting a question is worth 5 points.
 * - Posting an answer is worth 1 point.
 */
public final class Leaderboard implements  Observer{
    /**
     * Constructs a leaderboard for the specified forum.
     *
     * @throws IllegalArgumentException if the forum is null.
     */
    
    private final Forum forum;

    public void setUserPointMap(Map<User, Integer> userPointMap) {
        this.userPointMap = userPointMap;
    }

    private Map<User,Integer> userPointMap;
    
    public Leaderboard(Forum forum)  {
        if (forum==null) throw new IllegalArgumentException();
        this.forum = forum;
        this.forum.addObserver(this);
        this.userPointMap = new HashMap<>();
    }

    /**
     * Prints the leaderboard, using the following rules:
     * - Users are sorted by their point.
     * - If users have the same number of points, they are sorted by alphabetical order of names.
     * - Each user is printed as one line on the leaderboard.
     * - The line format is the following:
     * --- The line begins with a hash sign '#'
     * --- Immediately after the sign comes the user's rank.
     * ----- The user with most points has rank 1, the next user has rank 2, and so on.
     * ----- Two users with the same number of points have the same rank.
     * ------ If there are N users with the same number of points, N-1 ranks are skipped afterwards.
     * --- After the rank comes a space.
     * --- After that comes the user's name.
     * --- After the name comes a space.
     * --- After that comes the number of points.
     * - There is no line separator at the end, only between lines.
     * ===
     * [Begin example 0]
     * #1 George 9001
     * [End example 0]
     * [Begin example 1]
     * #1 George 9001
     * #2 Alice 100
     * #3 Bob 5
     * [End example 1]
     * [Begin example 2]
     * #1 George 9001
     * #2 Alice 100
     * #2 Carol 100
     * #4 Bob 5
     * [End example 2]
     */
    @Override
    public String toString() {
        List<Map.Entry<User,Integer>> sortUserList = sort();

        StringBuilder sb = new StringBuilder();
        int rank=0;
        int prevPoint = Integer.MAX_VALUE;
        for (int i = 0; i <sortUserList.size(); i++) {
            int points = sortUserList.get(i).getValue();
            String name = sortUserList.get(i).getKey().getName();
            StringBuilder sb1 = new StringBuilder();
            sb1.append("#");
            if (points<prevPoint) {
                rank++;
                prevPoint = points;
            }
            sb1.append(rank);
            sb1.append(" ");
            sb1.append(points);
            sb1.append(" ");
            sb1.append(name);
            if (i<sortUserList.size()-1) sb1.append(System.lineSeparator());
            sb.append(sb1.toString());
        }
        
        return sb.toString();
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (arg==null||observable==null) throw new IllegalArgumentException();
        Post post = (Post)arg;

        int score = userPointMap.getOrDefault(post.getAuthor(),0);

        if (post instanceof Question){
            score+=5;
        }else{
            score +=1;
        }
        userPointMap.put(post.getAuthor(),score);
    }
    
    private List<Map.Entry<User,Integer>> sort(){
        List<Map.Entry<User, Integer>> list = new ArrayList<>(this.userPointMap.entrySet());
        list.sort(new UserComparator());
        return list;
        
    }
    

}
