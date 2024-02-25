import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cronometro extends JFrame {
    private JLabel labelTiempo;
    private JButton startButton;
    private JButton stopButton;
    private JRadioButton forwardRadioButton;
    private JRadioButton backwardRadioButton;
    private JTextField initialMinutesField;
    private JTextField initialSecondsField;
    private JTextField currentMinutesField;
    private JTextField currentSecondsField;
    private Timer timer;
    private int segundos = 0;
    private int minutos = 0;
    private boolean running = false;
    private boolean backwardMode = false;

    public Cronometro() {
        setTitle("Cronómetro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        forwardRadioButton = new JRadioButton("Hacia adelante");
        forwardRadioButton.setSelected(true);
        forwardRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backwardMode = false;
                initialMinutesField.setEnabled(false);
                initialSecondsField.setEnabled(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(forwardRadioButton, gbc);

        backwardRadioButton = new JRadioButton("Hacia atrás");
        backwardRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backwardMode = true;
                initialMinutesField.setEnabled(true);
                initialSecondsField.setEnabled(true);
            }
        });
        gbc.gridy++;
        add(backwardRadioButton, gbc);

        ButtonGroup group = new ButtonGroup();
        group.add(forwardRadioButton);
        group.add(backwardRadioButton);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Tiempo inicial:"), gbc);

        initialMinutesField = new JTextField(2);
        initialMinutesField.setEnabled(false);
        gbc.gridx = 1;
        add(initialMinutesField, gbc);

        gbc.gridx = 2;
        add(new JLabel("min"), gbc);

        initialSecondsField = new JTextField(2);
        initialSecondsField.setEnabled(false);
        gbc.gridx = 3;
        add(initialSecondsField, gbc);

        gbc.gridx = 4;
        add(new JLabel("seg"), gbc);

        labelTiempo = new JLabel("00:00", SwingConstants.CENTER);
        labelTiempo.setFont(new Font("Arial", Font.BOLD, 40));
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy++;
        add(labelTiempo, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        startButton = new JButton("Iniciar");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        buttonPanel.add(startButton);

        stopButton = new JButton("Detener");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });
        stopButton.setEnabled(false);
        buttonPanel.add(stopButton);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5;
        add(buttonPanel, gbc);

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (backwardMode) {
                    if (minutos == 0 && segundos == 0) {
                        stop();
                        return;
                    }
                    segundos--;
                    if (segundos < 0) {
                        segundos = 59;
                        minutos--;
                    }
                } else {
                    segundos++;
                    if (segundos == 60) {
                        segundos = 0;
                        minutos++;
                    }
                }
                actualizarTiempo();
            }
        });

        setVisible(true);
    }

    private void actualizarTiempo() {
        String tiempo = String.format("%02d:%02d", minutos, segundos);
        labelTiempo.setText(tiempo);
    }

    private void start() {
        if (!running) {
            if (backwardMode) {
                try {
                    minutos = Integer.parseInt(initialMinutesField.getText());
                    segundos = Integer.parseInt(initialSecondsField.getText());
                    if (minutos < 0 || segundos < 0 || segundos >= 60) {
                        JOptionPane.showMessageDialog(this, "Por favor, introduzca un tiempo válido (mm:ss).", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, introduzca un tiempo válido (mm:ss).", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            running = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            timer.start();
        }
    }

    private void stop() {
        if (running) {
            running = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            timer.stop();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cronometro();
            }
        });
    }
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}