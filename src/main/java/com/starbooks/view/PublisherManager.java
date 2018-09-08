package com.starbooks.view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import quick.dbtable.*;
import com.starbooks.model.*;

public class PublisherManager extends JFrame
{
    static final long serialVersionUID = 2009L;
    
    private JPanel contentPane = null;
    
    private JPanel pnlFields = new JPanel();
    private JLabel lblPublisherName = new JLabel("Publisher Name");
    private JLabel lblPublisherID = new JLabel("Publisher ID");
    private JComboBox cbPublisherName = new JComboBox();
    private JTextField txtPublisherID = new JTextField("");
    private JButton btnFind = new JButton("Find");
    
    private DBTable tblBooks = new DBTable();
    private JScrollPane pnlGrids = new JScrollPane(tblBooks);
    
    private PublisherHibernateDAO publisherDAO = new PublisherHibernateDAO();
    private List publisherList = null;
    
    public PublisherManager()
    {
        publisherList = (List) publisherDAO.findAll(); 
        Iterator publishers = publisherList.iterator();
        while (publishers.hasNext())
        {
            Publisher publisher = (Publisher) publishers.next();
            cbPublisherName.addItem(publisher.getPublisherName());            
        }
        
        GridLayout fieldsLayout = new GridLayout(3, 2);
        pnlFields.setLayout(fieldsLayout);
        
        pnlFields.add(lblPublisherName);        
        pnlFields.add(cbPublisherName);
        pnlFields.add(lblPublisherID);        
        pnlFields.add(txtPublisherID);
        pnlFields.add(new JLabel());
        pnlFields.add(btnFind);
        
        btnFind.addActionListener(new PublisherFindListener(this));

        contentPane = (JPanel) this.getContentPane();
        contentPane.add(pnlFields, BorderLayout.NORTH);
        contentPane.add(pnlGrids, BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.setSize(850, 600);
        this.setTitle("Publisher Manager");
        this.setVisible(true);
    }
    
    public void findActionPerformed(ActionEvent e)
    {
        try
        {
            int selectedIndex = cbPublisherName.getSelectedIndex();
            Publisher publisher = (Publisher) publisherList.get(selectedIndex);
            String publisherId = publisher.getPublisherId();
            Collection books = publisherDAO.findBooksByPublisherId(publisherId);
            tblBooks.refreshDataObject(books, null);
            txtPublisherID.setText(publisherId);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    class PublisherFindListener implements ActionListener
    {
        private PublisherManager frame;
        
        public PublisherFindListener(PublisherManager frame)
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
        new PublisherManager();
    }
}
