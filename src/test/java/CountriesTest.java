import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {
    /*
    Create Table countries
            (
                    country_id CHAR(3),
    country_name VARCHAR(50),
    region_id SMALLINT
    );
    Insert into countries values ('AR', 'Argentina', 2);
    Insert into countries values ('AU', 'Australia', 3);
    Insert into countries values ('BE', 'Belgium', 1);
    Insert into countries values ('BR', 'Brasil', 2);
    Insert into countries values ('CA', 'Canada', 2);
    Insert into countries values ('CH', 'Switzerland', 1);
    Insert into countries values ('CN', 'China', 3);
    Insert into countries values ('DE', 'Germany', 1);
    Insert into countries values ('DK', 'Denmark', 1);
    Insert into countries values ('EG', 'Egypt', 4);
    Insert into countries values ('FR', 'France', 1);
    Insert into countries values ('IL', 'Israel', 4);
    Insert into countries values ('IN', 'India', 3);
    Insert into countries values ('IT', 'Italy', 1);
    Insert into countries values ('JP', 'Japan', 3);
    Insert into countries values ('KW', 'Kuwait', 4);
    Insert into countries values ('ML', 'Malaysia', 3);
    Insert into countries values ('MX', 'Nexico', 2);
    Insert into countries values ('NG', 'Nigeria', 4);
    Insert into countries values ('NL', 'Netherlands', 1);
    Insert into countries values ('SG', 'Singapore', 3);
    Insert into countries values ('UK', 'United Kingdom', 1);
    Insert into countries values ('US', 'United States of America', 2);
    Insert into countries values ('ZM', 'Zambia', 4);
    Insert into countries values ('ZW', 'Zimbabwe', 4);
    Select * From countries

     */
    /*
Given user connects to the database
When user sends the query to get the region ids from "countries" table
Then verify that the number of region ids greater than 1 is 17.
And user closes the connection
 */
@Test
    public void countriesTest() throws SQLException {
//    Given user connects to the database
    JdbcUtils.connectToDataBase("localhost", "arcane", "postgres", "2213");
    Statement statement = JdbcUtils.createStatement();


//    When user sends the query to get the region ids from "countries" table
    String sql = "select region_id from countries";
    ResultSet resultSet = statement.executeQuery(sql);

    List<Integer> ids = new ArrayList<>();
    while (resultSet.next()){
        ids.add(resultSet.getInt(1));
    }
    System.out.println("ids = " + ids);
//    Then verify that the number of region ids greater than 1 is 17.
    List<Integer> birdenBuyukIds = new ArrayList<>();

    for (Integer herBirEleman : ids){
        if (herBirEleman>1){
            birdenBuyukIds.add(herBirEleman);
        }
    }
    System.out.println("birdenBuyukIds = " + birdenBuyukIds);

    Assert.assertEquals(17, birdenBuyukIds.size());

//    And user closes the connection
    JdbcUtils.closeConnectionVeStatement();

}
}
