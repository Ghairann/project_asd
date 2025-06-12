import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

class Track {
    private String title;
    private String artist;
    private int orderAdded;

    public Track(String title, String artist, int orderAdded) {
        this.title = title;
        this.artist = artist;
        this.orderAdded = orderAdded;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getOrderAdded() {
        return orderAdded;
    }
}

public class PlaylistApp extends JFrame {
    private final ArrayList<Track> playlist = new ArrayList<>();
    private final DefaultTableModel tableModel;
    private int trackCounter = 0;

    public PlaylistApp() {
        setTitle("Playlist Manager");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        JLabel headerLabel = new JLabel("Playlist Manager");
        headerLabel.setBounds(270, 10, 160, 20);
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel);

        String[] columns = {"Title", "Artist", "Order Added"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 600, 200);
        scrollPane.getViewport().setBackground(Color.BLACK);
        add(scrollPane);

        JButton addButton = createButton("Add Track", 60, 280, e -> addTrack());
        JButton sortTitleButton = createButton("Sort by Title", 180, 280, e -> sortPlaylistByTitle());
        JButton sortArtistButton = createButton("Sort by Artist", 340, 280, e -> sortPlaylistByArtist());
        JButton sortOrderButton = createButton("Sort by Order", 500, 280, e -> sortPlaylistByOrder());

        add(addButton);
        add(sortTitleButton);
        add(sortArtistButton);
        add(sortOrderButton);

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 140, 40);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.addActionListener(action);
        return button;
    }

    private void addTrack() {
        String title = JOptionPane.showInputDialog("Enter Track Title:");
        if (title == null || title.trim().isEmpty()) return;

        String artist = JOptionPane.showInputDialog("Enter Artist Name:");
        if (artist == null || artist.trim().isEmpty()) return;

        playlist.add(new Track(title, artist, trackCounter++));
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Track track : playlist) {
            tableModel.addRow(new Object[]{
                track.getTitle(),
                track.getArtist(),
                track.getOrderAdded()
            });
        }
    }

    private void sortPlaylistByTitle() {
        playlist.sort((t1, t2) -> t1.getTitle().compareToIgnoreCase(t2.getTitle()));
        updateTable();
    }

    private void sortPlaylistByArtist() {
        playlist.sort((t1, t2) -> t1.getArtist().compareToIgnoreCase(t2.getArtist()));
        updateTable();
    }

    private void sortPlaylistByOrder() {
        playlist.sort((t1, t2) -> Integer.compare(t1.getOrderAdded(), t2.getOrderAdded()));
        updateTable();
    }

    public static void main(String[] args) {
        new PlaylistApp();
    }
}
