package application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Jpanelimagem extends JPanel {
    private BufferedImage imagem;

    public Jpanelimagem(String caminho) {
        try {
            imagem = ImageIO.read(getClass().getResource(caminho));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagem, 100, 0, this);
    }
}

public class TelaInicial extends JFrame {
    public TelaInicial() {
        showmenu();
    }

    public void showmenu() {
        setSize(400, 350);
        getContentPane().removeAll();
        repaint();
        revalidate();
        setTitle("Tela Inicial - Xadrez");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Jpanelimagem panel = new Jpanelimagem("/fundo.png");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(400,350);
        JLabel titulo = new JLabel("Bem-vindo ao Jogo de Xadrez!");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        JButton iniciarBtn = new JButton("Jogar");
        iniciarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        iniciarBtn.setMaximumSize(new Dimension(200, 50));
        iniciarBtn.addActionListener((ActionEvent e) -> {
            new ChessGUI(this);
        });

        JButton regrasBtn = new JButton("Regras");
        regrasBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        regrasBtn.setMaximumSize(new Dimension(200, 50));
        regrasBtn.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this,
                    "REGRAS BÁSICAS DE XADREZ\n\n" +
                            "1. Cada tipo de peça possui um padrão específico de movimentação:\n" +
                            "   - Rei, Rainha, Torre, Bispo, Cavalo, Peão\n\n" +
                            "2. O jogador com as peças brancas sempre inicia a partida.\n" +
                            "3. Os jogadores jogam alternadamente, sem pular turnos.\n\n" +
                            "4. Xeque: quando o rei está sob ameaça direta de captura.\n" +
                            "5. Xeque-mate: fim de jogo quando não há saída para o xeque.\n" +
                            "6. Empate: quando nenhum jogador pode dar xeque-mate.\n\n" +
                            "Divirta-se!",
                    "Regras do Xadrez",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton sairBtn = new JButton("Sair");
        sairBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sairBtn.setMaximumSize(new Dimension(200, 50));
        sairBtn.addActionListener((ActionEvent e) -> System.exit(0));

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(30));
        panel.add(iniciarBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(regrasBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(sairBtn);
        panel.add(Box.createVerticalGlue());

        add(panel);
        repaint();
        revalidate();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}