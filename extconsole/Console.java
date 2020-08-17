package fr.noctu.haxx.proto.extconsole;

import fr.noctu.haxx.proto.ProtoClient;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventGuiOpen;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.MovementUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings;
import org.lwjgl.input.Keyboard;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Console {
    public JFrame frame;
    public JTextPane console;
    public JTextField input;
    public JScrollPane scrollBar;
    public StyledDocument styledDocument;
    boolean trace = false;

    public Console() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame();
        frame.setTitle("ProtoClient Console");

        console = new JTextPane();
        console.setEditable(false);
        console.setFont(new Font("Franklin Gothic", Font.PLAIN, 16));
        console.setOpaque(false);

        styledDocument = console.getStyledDocument();

        input = new JTextField();
        input.setBorder(null);
        input.setEditable(true);
        input.setForeground(Color.WHITE);
        input.setFont(new Font("Franklin Gothic", Font.PLAIN, 20));
        input.setCaretColor(new Color(90, 90, 90));
        input.setBackground(new Color(90, 90, 90));

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = input.getText();
                if(text.length()>1) {
                    println(text, Color.WHITE);
                    if(text.equals("clear"))
                        clear();
                    executeCommand(text);
                    scrollBottom();
                    input.selectAll();
                }
            }
        });

        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        scrollBar = new JScrollPane(console);
        scrollBar.setBorder(null);
        scrollBar.setOpaque(false);
        scrollBar.getViewport().setOpaque(false);

        frame.add(input, BorderLayout.SOUTH);
        frame.add(scrollBar, BorderLayout.CENTER);
        frame.getContentPane().setBackground(new Color(64, 64, 64));
        frame.setLocationRelativeTo(null);
        frame.setSize(660, 350);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void scrollTop(){
        console.setCaretPosition(0);
    }

    private void scrollBottom() {
        console.setCaretPosition(console.getDocument().getLength());
    }

    private static String guiName = "";

    private void executeCommand(String command){
        String[] args = command.split(" ");
        String cmd = args[0];
        try{
            if(cmd.equalsIgnoreCase("bind")){
                if(args.length != 3)
                    println("Correct usage: bind <module name> <key>", new Color(235, 64, 45));
                else{
                    ProtoClient.instance.masterManager.moduleManager.getModule(args[1]).setKey(Keyboard.getKeyIndex(args[2].toUpperCase()));
                    println(args[1] + " has been binded to key " + args[2].toUpperCase(), Color.BLUE);
                }
            }

            if(cmd.equalsIgnoreCase("modules")){
                for(Module m : ProtoClient.instance.masterManager.moduleManager.getModules()){
                    println(m.getDisplayName(), Color.WHITE);
                }
            }

            if(cmd.equalsIgnoreCase("vclip")){
                MovementUtils.vClip(Double.parseDouble(args[1]));
                println("You has been tp to " + args[1] + " blocks up/down", Color.BLUE);
            }

            if(cmd.equalsIgnoreCase("guiname")){
                println(guiName, Color.red);
            }

            if(cmd.equalsIgnoreCase("gm0")) {
                Minecraft.getMinecraft().playerController.setGameType(WorldSettings.GameType.SURVIVAL);
                println("Your visual gamemode has been set to survival", Color.BLUE);
            }
            if(cmd.equalsIgnoreCase("gm1")) {
                Minecraft.getMinecraft().playerController.setGameType(WorldSettings.GameType.CREATIVE);
                println("Your visual gamemode has been set to creative", Color.BLUE);
            }

            if(cmd.equalsIgnoreCase("tp")){
                if(args.length != 4)
                    println("Correct usage: tp <x> <y> <z>", Color.RED);
                else{
                    int x = Integer.parseInt(args[1]);
                    int y = Integer.parseInt(args[2]);
                    int z = Integer.parseInt(args[3]);

                    MovementUtils.boxTeleport(x, y, z);
                    println("You have been teleported to " + x + ", " + y + ", " + z + " !", Color.BLUE);
                }
            }
        }catch(Exception e){
            println("Error: " + e.getMessage(), new Color(235, 64, 45));
        }
    }

    private void print(String text, boolean trace, Color c){
        Style style = console.addStyle("Style", null);
        StyleConstants.setForeground(style, c);
        if(trace){
            Throwable t = new Throwable();
            StackTraceElement[] stack = t.getStackTrace();

            text = "Console> " + text;
        }
        try{
            styledDocument.insertString(styledDocument.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    public void println(String text, Color c){
        print(text + "\n", true, c);
    }
    private void clear(){
        try {
            styledDocument.remove(0, styledDocument.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @EventTarget
    public void onGui(EventGuiOpen e){
        guiName = e.getGui().getClass().getName();
    }
}
