package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        String score = addToScore(player1Score);
        
        if (player1Score==player2Score) {
            if(score.equals("Deuce")) return score;
            score += "-All";
        } else if (player1Score>=4 || player2Score>=4) {
            score = checkAdvantageOrWin();
        } else {
            score += "-" + addToScore(player2Score);
        }
        return score;
    }
    
    private String addToScore(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";   
            default:
                return "Deuce";
        }
    }
    
    private String checkAdvantageOrWin() {
        int minusResult = player1Score-player2Score;
        if (minusResult==1) return "Advantage " + player1Name;
        else if (minusResult ==-1) return "Advantage " + player2Name;
        else if (minusResult>=2) return "Win for " + player1Name;
        else return "Win for " + player2Name;
    }
}