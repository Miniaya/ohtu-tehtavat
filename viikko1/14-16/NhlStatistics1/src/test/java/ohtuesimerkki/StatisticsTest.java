
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
    
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void hakuLoytaaPelaajan() {
        
        assertEquals(readerStub.getPlayers().get(0), stats.search("Semenko"));
    }
    
    @Test
    public void palauttaaNullJosEiLoydaPelaajaa() {
        
        assertEquals(null, stats.search("Henna"));
    }
    
    @Test
    public void tiimiOikein() {
        ArrayList<Player> team = new ArrayList<>();
        
        team.add(readerStub.getPlayers().get(1));
        
        assertEquals(team, stats.team("PIT"));
    }
    
    @Test
    public void topScorejaOikeaMaara() {
        
        assertEquals(2, stats.topScorers(2).size());
    }
}
