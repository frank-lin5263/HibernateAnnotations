package com.starbooks.view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.starbooks.model.*;

public class BookManager extends JFrame
{
    static final long serialVersionUID = 2009L;
    
    private JPanel contentPane = null;
    
    private JPanel pnlFields = new JPanel();
    private JLabel lblBookID = new JLabel("Book ID");
    private JLabel lblISBN = new JLabel("ISBN");
    private JLabel lblTitle = new JLabel("Title");
    private JLabel lblReleaseDate = new JLabel("Release Date");
    private JLabel lblListPrice = new JLabel("List Price");
    private JLabel lblPublisherID = new JLabel("Publisher ID");
    private JLabel lblAuthors = new JLabel("Authors");
    private JTextField txtBookID = new JTextField("5");
    private JTextField txtISBN = new JTextField("");
    private JTextField txtTitle = new JTextField("");
    private JTextField txtReleaseDate = new JTextField("");
    private JTextField txtListPrice = new JTextField("");
    private JTextField txtPublisherID = new JTextField("");
    private JTextField txtAuthors = new JTextField("");
    private JButton btnFind = new JButton("Find");
    
    private JPanel pnlGrids = new JPanel();
    private JLabel lblCoverImage = new JLabel("Cover Image");
    private JLabel imgCoverImage = new JLabel("");
    private JScrollPane pnlCoverImage = new JScrollPane(imgCoverImage);
       
    public BookManager()
    {
        GridLayout fieldsLayout = new GridLayout(8, 2);
        pnlFields.setLayout(fieldsLayout);
        
        pnlFields.add(lblBookID);        
        pnlFields.add(txtBookID);
        pnlFields.add(new JLabel());
        pnlFields.add(btnFind);
        
        pnlFields.add(lblISBN);
        pnlFields.add(txtISBN);       
        pnlFields.add(lblTitle);
        pnlFields.add(txtTitle);
        pnlFields.add(lblReleaseDate);
        pnlFields.add(txtReleaseDate);
        pnlFields.add(lblListPrice);
        pnlFields.add(txtListPrice);
        pnlFields.add(lblPublisherID);
        pnlFields.add(txtPublisherID);
        pnlFields.add(lblAuthors);
        pnlFields.add(txtAuthors);        
        
        btnFind.addActionListener(new BookFindListener(this));

        GridLayout gridsLayout = new GridLayout(1, 2);
        pnlGrids.setLayout(gridsLayout);
                
        pnlGrids.add(lblCoverImage);
        pnlGrids.add(pnlCoverImage);
        
        contentPane = (JPanel) this.getContentPane();
        contentPane.add(pnlFields, BorderLayout.NORTH);
        contentPane.add(pnlGrids, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.setLocation(100, 100);
        this.setSize(850, 600);
        this.setTitle("Book Manager");
        this.setVisible(true);
    }
    
    public void findActionPerformed(ActionEvent e)
    {
        BookHibernateDAO bookDAO = new BookHibernateDAO();
        Book book = 
            bookDAO.findByPrimaryKey(Integer.parseInt(txtBookID.getText()));
        txtISBN.setText(book.getIsbn());
        txtTitle.setText(book.getTitle());
        txtReleaseDate.setText(book.getReleaseDate().toString());
        txtListPrice.setText(String.valueOf(book.getListPrice()));
        
        txtPublisherID.setText(book.getPublisher().getPublisherName());
        //txtPublisherID.setText(book.getPubId());
        
        Icon coverImage = new ImageIcon(book.getCoverImage());
        imgCoverImage.setIcon(coverImage);
        
        Iterator<Author> authors = book.getAuthors().iterator();
        StringBuffer buffer = new StringBuffer();
        while (authors.hasNext())
        {
            Author author = authors.next();
            buffer.append(author.getAuthorName());
            if (authors.hasNext()) buffer.append(", ");
        }
        txtAuthors.setText(buffer.toString());
    }

    class BookFindListener implements ActionListener
    {
        private BookManager frame;
        
        public BookFindListener(BookManager frame)
        {
            this.frame = frame;
        }    
        
        public void actionPerformed(ActionEvent e)
        {
            frame.findActionPerformed(e);                
        }        
    }
    
    public static void main(String[] args)
    {
        new BookManager();
    }
}
