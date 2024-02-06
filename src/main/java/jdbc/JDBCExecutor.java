package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

    public static void main(String... args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("db",
                "rps2", "postgres", "password");

        try {
            Connection connection = dcm.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT player_name, password FROM player");
            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " +
                        resultSet.getString(2));
            }

            PlayerDAO playerDAO = new PlayerDAO(connection);

            Player player = new Player();
            player.setPlayerName("isaac");
            player = playerDAO.findById(player);
            player = playerDAO.update(player);
            System.out.println(player.toString());

            Player newPlayer = new Player();
            newPlayer.setPlayerName("Lizelle");
            newPlayer.setPassword("password1");
            newPlayer.setWins(9001);

            //newPlayer = playerDAO.create(newPlayer);
            System.out.println(newPlayer.toString());

            GameDAO gameDAO = new GameDAO(connection);
            Game newGame = new Game();
            newGame.setGameId(2);
            newGame = gameDAO.findById(newGame);
            System.out.println(newGame);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}