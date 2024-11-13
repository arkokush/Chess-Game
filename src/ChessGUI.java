import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Oct 24, 2024

public class ChessGUI extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 600;
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    private final ChessBoard board;
    private final King kingW;
    private final King kingB;
    private final ArrayList<ChessPiece> pieces;
    private int colorTurn;
    private int xS = -75, yS = -75;
    private int cursorX = 0, cursorY = 0;
    private boolean pieceSelected;

    public ChessGUI() throws IOException {
        colorTurn = WHITE;
        pieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(i * 75, 450, WHITE));
            pieces.add(new Pawn(i * 75, 75, BLACK));

        }
        pieces.add(new Rook(0, 525, WHITE));
        pieces.add(new Knight(75, 525, WHITE));
        pieces.add(new Bishop(150, 525, WHITE));
        pieces.add(new Queen(225, 525, WHITE));
        pieces.add(new Bishop(375, 525, WHITE));
        pieces.add(new Knight(450, 525, WHITE));
        pieces.add(new Rook(525, 525, WHITE));
        kingW = new King(300, 525, WHITE);
        pieces.add(kingW);
        pieces.add(new Rook(0, 0, BLACK));
        pieces.add(new Knight(75, 0, BLACK));
        pieces.add(new Bishop(150, 0, BLACK));
        pieces.add(new Queen(225, 0, BLACK));
        pieces.add(new Bishop(375, 0, BLACK));
        pieces.add(new Knight(450, 0, BLACK));
        pieces.add(new Rook(525, 0, BLACK));
        kingB = new King(300, 0, BLACK);
        pieces.add(kingB);


        this.setFocusable(true); // Lets KeyListener Detect the panel
        this.addKeyListener(this); // Adds the KeyListener
        this.addMouseListener(this); // Adds Mouselistener
        this.addMouseMotionListener(this); // Adds Mouse Motion Listenerw
        board = new ChessBoard(600, 600);
        Timer timer = new Timer(10, new ActionListener() // Creates Timer in order to keep code running
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("Time...");
                repaint();

            }

        });
        timer.start(); // Starts Timer
    }

    public static void runChessGUI() throws IOException {
        JFrame frame = new JFrame("Chess Game");
        frame.add(new ChessGUI()); // Adds Game
        frame.pack(); // Adds Dimension
        frame.setLocationRelativeTo(null); // Centers Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when close
        frame.setVisible(true); // Makes it Visible
    }

    public static ArrayList<Rectangle> getHitbox(ArrayList<ChessPiece> pieces) {
        ArrayList<Rectangle> pHitboxes = new ArrayList<>();
        for (ChessPiece p : pieces) {
            Rectangle h = p.getHitbox();
            pHitboxes.add(h);
        }
        return pHitboxes;

    }

    public static void main(String[] args) throws Exception {
        ChessGUI.runChessGUI();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; // Casts into Graphics 2D

        // Color the Background
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        board.draw(g2);
        for (ChessPiece piece : pieces) {

            ChessPieceImage pieceImage;
            if (piece.isPickedUp()) {
                pieceImage = piece.draw(cursorX, cursorY);
            } else {
                pieceImage = piece.draw();
            }

            g2.drawImage(pieceImage.getImg(), pieceImage.getX(), pieceImage.getY(), pieceImage.getWidth(), pieceImage.getHeight(), null);
        }
        g2.setColor(new Color(255, 0, 0, 128));
        g2.fillRect(xS, yS, 75, 75);

    }

    @Override
    public Dimension getPreferredSize() {
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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        handleMousePress(e);
        System.out.println("Piece selected: "+pieceSelected);}

    private void handleMousePress(MouseEvent e) {
        Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);
        ArrayList<Rectangle> boxes = createBoardRectangles();
        ArrayList<Rectangle> pHitboxes = getHitbox(pieces);

            for (Rectangle b : boxes) {
                if (mouse.intersects(b)) {
                    xS = (int) b.getX();
                    yS = (int) b.getY();
                    if (placePiece()) { // Only change turn if a piece is placed
                        if (colorTurn == WHITE) {
                            colorTurn = BLACK;
                        } else {
                            colorTurn = WHITE;
                        }

                    }
                }
            }
         if(!pieceSelected) {
            pickUpPiece(mouse, pHitboxes);
        }
    }

    public ArrayList<Rectangle> createBoardRectangles() {
        ArrayList<Rectangle> boxes = new ArrayList<>();
        for (int w = 0; w < 8; w++) {
            for (int h = 0; h < 8; h++) {
                boxes.add(new Rectangle(w * 75, h * 75, 75, 75));
            }
        }
        return boxes;
    }

    public boolean placePiece() {
        boolean piecePlaced = false;

        for (int i = pieces.size() - 1; i >= 0; i--) {
            ChessPiece piece = pieces.get(i);
            int x1 = piece.getX();
            int y1 = piece.getY();
            boolean didMove = piece.didMove;
            if (piece.isPickedUp()) {
                piece.move(xS, yS, pieces);
                System.out.println(piece + " was placed down");
                if ((colorTurn == WHITE && kingW.inCheck(pieces)) || colorTurn == BLACK && kingB.inCheck(pieces)) {
                    piece.setX(x1);
                    piece.setY(y1);
                    piece.setDidMove(didMove);

                }
                if (!(x1 == piece.getX() && y1 == piece.getY()) && (piece.isPickedUp() || piece.getClass() == King.class)) {
                    piecePlaced = true;
                }
                pieceSelected = false;
                piece.setPickedUp(false);


            }

        }
        System.out.println("Piece Placed:" + piecePlaced);
        return piecePlaced;
    }

    public void pickUpPiece(Rectangle mouse, ArrayList<Rectangle> pHitboxes) {
        for (Rectangle p : pHitboxes) {
            if (mouse.intersects(p)) {
                ChessPiece piece = ChessPiece.getPieceAt((int) p.getX(), (int) p.getY(),pieces);
                if(piece!=null){
                if (!piece.isPickedUp() && piece.getColor() == colorTurn) {
                    piece.setPickedUp(true);
                    System.out.println(piece + " was picked up");
                    System.out.println(colorTurn + " :turn");
                    pieceSelected = true;
                }
            }}
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        cursorX = e.getX();
        cursorY = e.getY();

    }

}