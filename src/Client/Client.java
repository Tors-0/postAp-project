package Client;

import Controls.Player;
import Engine.OpenGlEngine;

import java.awt.event.KeyEvent;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Client {



    public static void main(String[] args) {
        OpenGlEngine Engine = new OpenGlEngine();
        Engine.run();

        Player jeremy = new Player();
        while(!glfwWindowShouldClose(Engine.getWindow())) {
            jeremy.update();
        }
    }

}