package org.example;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class MainTest {


    @org.junit.jupiter.api.Test
    void handleInput(){
        //Empty spaces
        assertEquals("That's an empty space. Not a number of columns.", Main.handleInput(""));
        assertEquals("That's an empty space. Not a number of columns.", Main.handleInput("\t"));
        assertEquals("That's an empty space. Not a number of columns.", Main.handleInput("\n"));
        assertEquals("That's an empty space. Not a number of columns.", Main.handleInput(" "));
        assertEquals("That's an empty space. Not a number of columns.", Main.handleInput("           "));

        // Numbers.
        assertEquals("Cannot have zero or less columns", Main.handleInput("-13"));
        assertEquals("Cannot have zero or less columns", Main.handleInput("-23556"));
        assertEquals("Cannot have zero or less columns", Main.handleInput("-1"));
        assertEquals("Cannot have zero or less columns", Main.handleInput("0"));

        assertEquals("", Main.handleInput("45"));
        assertEquals("", Main.handleInput("1"));
        assertEquals("", Main.handleInput("2332"));


        // Multiple characters. The most likely case is the user hits two keys right next to each other.
        assertEquals("That's a string, not a number.", Main.handleInput("de"));
        assertEquals("That's a string, not a number.", Main.handleInput("ew"));
        assertEquals("That's a string, not a number.", Main.handleInput("cat"));
        assertEquals("That's a string, not a number.", Main.handleInput("what"));
        assertEquals("That's a string, not a number.", Main.handleInput("wq"));

        // "Special" characters.
        assertEquals("That's a string, not a number.", Main.handleInput("!"));
        assertEquals("That's a string, not a number.", Main.handleInput("&"));
        assertEquals("That's a string, not a number.", Main.handleInput("\\"));
        assertEquals("That's a string, not a number.", Main.handleInput("?"));
        assertEquals("That's a string, not a number.", Main.handleInput(","));

        // And finally, the game as it should be.
        assertEquals("That's a string, not a number.", Main.handleInput("t"));
        assertEquals("That's a string, not a number.", Main.handleInput("h"));
        assertEquals("That's a string, not a number.", Main.handleInput("i"));
        assertEquals("That's a string, not a number.", Main.handleInput("n"));
        assertEquals("That's a string, not a number.", Main.handleInput("G"));
    }


}
