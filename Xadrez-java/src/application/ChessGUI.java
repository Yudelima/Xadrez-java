package application;

import chess.ChessMatch;
import chess.ChessPiece;

import chess.ChessPosition;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGUI {
    private TelaInicial frame;
    private JPanel boardPanel;
    private JPanel menuPanel;
    private JButton[][] buttons;
    private ChessMatch chessMatch;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private boolean mustMoveKing = false;
    private java.util.List<Point> highlightedSquares = new java.util.ArrayList<>();

    public ChessGUI( TelaInicial frame) {
     this.frame=frame;
        startGame();
    }

    // Verifica se o jogador atual está em xeque-mate
    private boolean isCheckMate() {
        return chessMatch.getCheckMate();
    }

    // Desabilita todos os botões do tabuleiro
    private void disableBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    private void startGame() {

        chessMatch = new ChessMatch();
        boardPanel = new JPanel(new GridLayout(8, 8));
        buttons = new JButton[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 32));
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
                boardPanel.add(button);
            }
        }
        frame.setSize(650, 650);
        frame.setContentPane(boardPanel);
        frame.revalidate();
        frame.repaint();
        selectedRow = -1;
        selectedCol = -1;
        mustMoveKing = false;
        highlightedSquares.clear();
        updateBoard();
    }

    private void onSquareClick(int row, int col) {
        if (selectedRow == -1) {
            // Primeira seleção
            ChessPiece piece = chessMatch.getPieces()[row][col];
            if (piece != null) {
                // Show warning if in check, but allow any piece selection
                if (chessMatch.getCheck()) {
                    JOptionPane.showMessageDialog(frame, "Você está em xeque! Proteja seu rei.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
                selectedRow = row;
                selectedCol = col;
                highlightedSquares.clear();
                // Highlight possible moves
                ChessPiece selectedPiece = chessMatch.getPieces()[row][col];
                if (selectedPiece != null) {
                    boolean[][] possibleMoves = selectedPiece.possibleMoves();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (possibleMoves[i][j]) {
                                highlightedSquares.add(new Point(i, j));
                            }
                        }
                    }
                }
                updateBoard();
            }
        } else {
            try {
                chessMatch.performChessMove(
                        new ChessPosition((char) ('a' + selectedCol), 8 - selectedRow),
                        new ChessPosition((char) ('a' + col), 8 - row));
                // After move, check for checkmate first
                if (isCheckMate()) {
                    String winner = chessMatch.getCurrentPlayer() == chess.Color.WHITE ? "Preto" : "Branco";
                    showEndGameDialog(winner);
                    disableBoard();
                } else if (chessMatch.getCheck()) {
                    // If only check, show warning but do not end game
                    JOptionPane.showMessageDialog(frame, "Xeque! Proteja seu rei.", "Xeque", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            selectedRow = -1;
            selectedCol = -1;
            highlightedSquares.clear();
            updateBoard();
        }
    }

    private void updateBoard() {
        ChessPiece[][] pieces = chessMatch.getPieces();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = buttons[i][j];
                if (pieces[i][j] == null) {
                    button.setText("");
                } else {
                    button.setText(pieces[i][j].toString());
                    button.setForeground(pieces[i][j].getColor() == chess.Color.WHITE ? Color.BLUE : Color.BLACK);
                }
                // Reset background
                button.setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);
                // Highlight selected
                if (i == selectedRow && j == selectedCol) {
                    button.setBackground(Color.YELLOW);
                }
                // Highlight possible moves
                for (Point p : highlightedSquares) {
                    if (p.x == i && p.y == j) {
                        button.setBackground(Color.GREEN);
                    }
                }
            }
        }
    }

    private void showEndGameDialog(String winner) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Xeque-mate! Jogador " + winner + " venceu!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        JOptionPane mensagem=new JOptionPane(panel,JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog=mensagem.createDialog(frame,"Fim de Jogo");
        panel.add(label);
        JButton menuButton = new JButton("Retornar ao Menu");
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.addActionListener(e -> {
            dialog.dispose();
            frame.getContentPane().removeAll();
            frame.dispose();
            new TelaInicial().setVisible(true);
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(menuButton);
        //JOptionPane.showMessageDialog(frame, panel, "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
    dialog.setVisible(true);
    }
}