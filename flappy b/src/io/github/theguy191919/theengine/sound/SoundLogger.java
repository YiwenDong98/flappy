/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.theengine.sound;

import paulscode.sound.SoundSystemLogger;

/**
 *
 * @author root
 */
public class SoundLogger extends SoundSystemLogger{
    /**
 * Prints a message.
 * @param message Message to print.
 * @param indent Number of tabs to indent the message.
 */
    public void message( String message, int indent )
    {
        String messageText;
        // Determine how many spaces to indent:
        String spacer = "";
        for( int x = 0; x < indent; x++ )
        {
            spacer += "    ";
        }
        // indent the message:
        messageText = spacer + message;
        
        // Print the message:
        //System.out.println( messageText );
    }
    
/**
 * Prints an important message.
 * @param message Message to print.
 * @param indent Number of tabs to indent the message.
 */
    public void importantMessage( String message, int indent )
    {
        String messageText;
        // Determine how many spaces to indent:
        String spacer = "";
        for( int x = 0; x < indent; x++ )
        {
            spacer += "    ";
        }
        // indent the message:
        messageText = spacer + message;
        
        // Print the message:
        //System.out.println( messageText );
    }
    
/**
 * Prints the specified message if error is true.
 * @param error True or False.
 * @param classname Name of the class checking for an error.
 * @param message Message to print if error is true.
 * @param indent Number of tabs to indent the message.
 * @return True if error is true.
 */
    public boolean errorCheck( boolean error, String classname, String message, 
                             int indent )
    {
        if( error )
            errorMessage( classname, message, indent );
        return error;
    }
    
/**
 * Prints the classname which generated the error, followed by the error 
 * message.
 * @param classname Name of the class which generated the error.
 * @param message The actual error message.
 * @param indent Number of tabs to indent the message.
*/
    public void errorMessage( String classname, String message, int indent )
    {
        String headerLine, messageText;
        // Determine how many spaces to indent:
        String spacer = "";
        for( int x = 0; x < indent; x++ )
        {
            spacer += "    ";
        }
        // indent the header:
        headerLine = spacer + "Error in class '" + classname + "'";
        // indent the message one more than the header:
        messageText = "    " + spacer + message;
        
        // Print the error message:
        //System.out.println( headerLine );
        //System.out.println( messageText );
    }
    
/**
 * Prints an exception's error message followed by the stack trace.
 * @param e Exception containing the information to print.
 * @param indent Number of tabs to indent the message and stack trace.
 */
    public void printStackTrace( Exception e, int indent )
    {
        printExceptionMessage( e, indent );
        importantMessage( "STACK TRACE:", indent );
        if( e == null )
            return;
        
        StackTraceElement[] stack = e.getStackTrace();
        if( stack == null )
            return;
        
        StackTraceElement line;
        for( int x = 0; x < stack.length; x++ )
        {
            line = stack[x];
            if( line != null )
                message( line.toString(), indent + 1 );
        }
    }
    
/**
 * Prints an exception's error message.
 * @param e Exception containing the message to print.
 * @param indent Number of tabs to indent the message.
 */
    public void printExceptionMessage( Exception e, int indent )
    {
        importantMessage( "ERROR MESSAGE:", indent );
        if( e.getMessage() == null )
            message( "(none)", indent + 1 );
        else
            message( e.getMessage(), indent + 1 );
    }
}
