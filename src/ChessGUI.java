import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//Arkady Kokush
//Date: Oct 24, 2024

public class ChessGUI extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
    public int getColorTurn()
    {
        return colorTurn;
    }

    public void setColorTurn(int colorTurn)
    {
        this.colorTurn = colorTurn;
    }

    public int getxS()
    {
        return xS;
    }

    public void setxS(int xS)
    {
        this.xS = xS;
    }

    public int getyS()
    {
        return yS;
    }

    public void setyS(int yS)
    {
        this.yS = yS;
    }

    public int getCursorX()
    {
        return cursorX;
    }

    public void setCursorX(int cursorX)
    {
        this.cursorX = cursorX;
    }

    public int getCursorY()
    {
        return cursorY;
    }

    public void setCursorY(int cursorY)
    {
        this.cursorY = cursorY;
    }

    public int getScreen()
    {
        return screen;
    }

    public void setScreen(int screen)
    {
        this.screen = screen;
    }

    public ChessBoard getBoard()
    {
        return board;
    }

    public void setBoard(ChessBoard board)
    {
        this.board = board;
    }

    public ArrayList<ChessPiece> getPieces()
    {
        return pieces;
    }

    public void setPieces(ArrayList<ChessPiece> pieces)
    {
        this.pieces = pieces;
    }

    public ArrayList<ChessPiece> getpBlack()
    {
        return pBlack;
    }

    public void setpBlack(ArrayList<ChessPiece> pBlack)
    {
        this.pBlack = pBlack;
    }

    public ArrayList<ChessPiece> getpWhite()
    {
        return pWhite;
    }

    public void setpWhite(ArrayList<ChessPiece> pWhite)
    {
        this.pWhite = pWhite;
    }

    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 600;
    public static final int GAME_OVER = 1;
    public static final int GAME_MENU = 2;
    public static final int GAME_RUN = 3;
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    private int colorTurn;
    private int xS = -75, yS = -75;
    private int cursorX = 0, cursorY = 0;
    private int screen = 0;
    private ChessBoard board;
    private ArrayList<ChessPiece> pieces;
    private ArrayList<ChessPiece> pBlack;
    private ArrayList<ChessPiece> pWhite;

    public ChessGUI() throws IOException {
        colorTurn = WHITE;
        pieces = new ArrayList<>();
        pBlack = new ArrayList<>();
        pWhite = new ArrayList<>();

        for (int i = 0; i < 8; i++)
        {
            pWhite.add(new Pawn(i * 75, 450, WHITE));
            pBlack.add(new Pawn(i * 75, 75, BLACK));

        }
        pWhite.add(new Rook(0, 525, WHITE));
        pWhite.add(new Knight(75, 525, WHITE));
        pWhite.add(new Bishop(150, 525, WHITE));
        pWhite.add(new Queen(225, 525, WHITE));
        pWhite.add(new King(300, 525, WHITE));
        pWhite.add(new Bishop(375, 525, WHITE));
        pWhite.add(new Knight(450, 525, WHITE));
        pWhite.add(new Rook(525, 525, WHITE));

        pBlack.add(new Rook(0, 0, BLACK));
        pBlack.add(new Knight(75, 0, BLACK));
        pBlack.add(new Bishop(150, 0, BLACK));
        pBlack.add(new Queen(225, 0, BLACK));
        pBlack.add(new King(300, 0, BLACK));
        pBlack.add(new Bishop(375, 0, BLACK));
        pBlack.add(new Knight(450, 0, BLACK));
        pBlack.add(new Rook(525, 0, BLACK));

        for (int i = 0; i < 16; i++)
        {
            pieces.add(pWhite.get(i));
            pieces.add(pBlack.get(i));
        }
        this.setFocusable(true); // Lets KeyListener Detect the panel
        this.addKeyListener(this); // Adds the KeyListener
        this.addMouseListener(this); // Adds Mouselistener
        this.addMouseMotionListener(this); // Adds Mouse Motion Listenerw
        board = new ChessBoard(600, 600);
        Timer timer = new Timer(10, new ActionListener() // Creates Timer in order to keep code running
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // System.out.println("Time...");
                repaint();

            }

        });
        timer.start(); // Starts Timer
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g; // Casts into Graphics 2D

        // Color the Background
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        board.draw(g2);
        for (ChessPiece piece : pieces)
        {
            if (piece.isPickedUp())
                piece.draw(g2, cursorX, cursorY);
            else
                piece.draw(g2);
        }
        g2.setColor(new Color(255, 0, 0, 128));
        g2.fillRect(xS, yS, 75, 75);

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT); // Sets Dimension
    }

    @Override
    public void keyTyped(KeyEvent e) // KeyListener Method
    {
    }

    @Override
    public void keyPressed(KeyEvent e) // KeyListener Method
    {

    }

    @Override
    public void keyReleased(KeyEvent e) // KeyListener Method
    {
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        handleMousePress(e);
    }

    private void handleMousePress(MouseEvent e)
    {
        Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);
        ArrayList<Rectangle> boxes = createBoardRectangles();
        ArrayList<Rectangle> pHitboxes = this.getHitbox(pieces);

        for (Rectangle b : boxes)
        {
            if (mouse.intersects(b))
            {
                xS = (int) b.getX();
                yS = (int) b.getY();
                if (placePiece())
                { // Only change turn if a piece is placed
                    if (colorTurn == WHITE)
                        colorTurn = BLACK;
                    else
                        colorTurn = WHITE;
                }
            }
        }
        pickUpPiece(mouse, pHitboxes);
    }

    public ArrayList<Rectangle> createBoardRectangles()
    {
        ArrayList<Rectangle> boxes = new ArrayList<>();
        for (int w = 0; w < 8; w++)
        {
            for (int h = 0; h < 8; h++)
            {
                boxes.add(new Rectangle(w * 75, h * 75, 75, 75));
            }
        }
        return boxes;
    }


    public boolean placePiece() {
        boolean piecePlaced = false;

        for (int i = pieces.size()-1; i >=0; i--) {
            ChessPiece piece = pieces.get(i);
            int x1 = piece.getX();
            int y1 = piece.getY();
            if (piece.isPickedUp()) {
                piece.move(xS, yS, pieces);
                // Check if the new position captures an opponent's piece
                for (int j = pieces.size()-1; j >=0; j--) {
                    ChessPiece target = pieces.get(j);
                    if (target.getX() == xS && target.getY() == yS && target.getColor()
                            != piece.getColor() && piece.canMove(xS,yS,pieces)) {
                        pieces.remove(j); // Remove the captured piece
                        break;
                    }
                }

                System.out.println(piece + " was placed down");
                if(!(x1==piece.getX()&&y1==piece.getY())&&piece.isPickedUp())
                    piecePlaced = true;
                piece.setPickedUp(false);


            }

        }
        return piecePlaced;
    }

    public void pickUpPiece(Rectangle mouse, ArrayList<Rectangle> pHitboxes)
    {
        for (Rectangle p : pHitboxes)
        {
            if (mouse.intersects(p))
            {
                ChessPiece piece = pieces.get(pHitboxes.indexOf(p));
                if (!piece.isPickedUp() && piece.getColor() == colorTurn)
                {
                    piece.setPickedUp(true);
                    System.out.println(piece + " was picked up");
                    System.out.println(colorTurn + " :turn");

                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    public static void runChessGUI() throws IOException {
        JFrame frame = new JFrame("Chess Game");
        frame.add(new ChessGUI()); // Adds Game
        frame.pack(); // Adds Dimension
        frame.setLocationRelativeTo(null); // Centers Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when close
        frame.setVisible(true); // Makes it Visible
    }

    public static ArrayList<Rectangle> getHitbox(ArrayList<ChessPiece> pieces)
    {
        ArrayList<Rectangle> pHitboxes = new ArrayList<>();
        for (ChessPiece p : pieces)
        {
            Rectangle h = p.getHitbox();
            pHitboxes.add(h);
        }
        return pHitboxes;

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        cursorX = e.getX();
        cursorY = e.getY();

    }

    public static void main(String[] args) throws Exception {
        ChessGUI.runChessGUI();
    }

}