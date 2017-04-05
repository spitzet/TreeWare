/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yoscatreeware;

/**
 *
 * @author MEAT WAGON
 */
public class IncorrectEntryException extends Exception {
    int type;// 1 = empty 2= Ä detected

    /**
     * Creates a new instance of
     * <code>IncorrectEntryException</code> without detail message.
     */
    public IncorrectEntryException() {
    }

    /**
     * Constructs an instance of
     * <code>IncorrectEntryException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public IncorrectEntryException(String msg, int i) {
       
        super(msg);
         type = i;
    }
}
