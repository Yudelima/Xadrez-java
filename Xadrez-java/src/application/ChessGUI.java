
package application;

import chess.ChessMatch;
import chess.ChessPiece;

import chess.ChessPosition;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGUI {
    private JFrame frame;
    private JButton[][] buttons;
    private ChessMatch chessMatch;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public ChessGUI() {
        chessMatch = new ChessMatch();
        initialize();
        updateBoard();
    }

    private void initialize() {
        frame = new JFrame("Xadrez - Jogo Simples");
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(8, 8));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 32));
                button.setFocusPainted(false);
                button.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                final int r = row;
                final int c = col;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onSquareClick(r, c);
                    }
                });
                buttons[row][col] = button;
                frame.add(button);
            }
        }

        frame.setVisible(true);
    }

    private void onSquareClick(int row, int col) {
        if (selectedRow == -1) {
            // Primeira seleção
            if (chessMatch.getPieces()[row][col] != null) {
                selectedRow = row;
                selectedCol = col;
            }
        } else {
            try {
                chessMatch.performChessMove(
                        new ChessPosition((char) ('a' + selectedCol), 8 - selectedRow),
                        new ChessPosition((char) ('a' + col), 8 - row));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            selectedRow = -1;
            selectedCol = -1;
            updateBoard();
        }
    }

    private void updateBoard() {
        ChessPiece[][] pieces = chessMatch.getPieces();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] == null) {
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setText(pieces[i][j].toString());
                    buttons[i][j].setForeground(pieces[i][j].getColor() == chess.Color.WHITE ? Color.BLUE : Color.BLACK);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}
