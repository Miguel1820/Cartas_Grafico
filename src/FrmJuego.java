import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class FrmJuego extends JFrame {

    JPanel pnlJugador1, pnlJugador2;
    JTabbedPane tpJugadores;

    public FrmJuego() {
        setSize(700, 250);
        setTitle("Juguemos al apuntado!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        //Botones
        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        getContentPane().add(btnVerificar);

        JButton btnOrdenar = new JButton("Ordenar");
        btnOrdenar.setBounds(230, 10, 100, 25);
        getContentPane().add(btnOrdenar);

        //Tabla creada
        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 40, 650, 150);
        getContentPane().add(tpJugadores);

        //Color de tabla para cada jugador
        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(16, 139, 37));
        pnlJugador1.setLayout(null);

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        //Pestaña para cada jugador (tipo excel)
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raúl Vidal", pnlJugador2);
        
        //Escuchador de eventos (saber si hizo click en el boton, haga esto...)
        btnRepartir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repartirCartas();
            }
        });

        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarJugador();
            }
        });

        btnOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarCartas();
            }
        });
    }

    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();

    private void repartirCartas() {
        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);
        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);
    }

    private void verificarJugador() {
        int pestañaSeleccionada = tpJugadores.getSelectedIndex();
        String mensaje = "";
        switch (pestañaSeleccionada) {
            case 0:
                mensaje = jugador1.getGrupos();
                break;
            case 1:
                mensaje = jugador2.getGrupos();
                break;
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void ordenarCartas() {
        jugador1.ordenar();
        jugador1.mostrar(pnlJugador1);
        jugador2.ordenar();
        jugador2.mostrar(pnlJugador2);
    }

}
