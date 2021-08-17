package myFrames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import myObject.Book;
import mySQLManager.BookAdder;
import mySQLManager.DBmanager;
import myUtils.BookToArrays;

public class BookReturnPanel extends JPanel {
	private JTable table;
	private JScrollPane tablePane;
	private JPanel bottomPanel;
	private JSplitPane splitPane;
	private JButton btn_Return;
	private List<Book> books;
	private String colNames[] = { "ID", "书名", "借书者" };
	private String Books[][] = {{"无","无","无"}};

	public BookReturnPanel() {
		setBounds(0, 0, 1600, 800);
		
		btn_Return = new JButton("归还书籍");

		books = DBmanager.getBooksReaderBorrowed(MainFrame.instance.getReader());
		if (!books.isEmpty()) {
			int row = 0;
			for (Book book : books) {
				for (int col = 0; col < 3; col++) {
					if (col == 1)
						Books[row][col] = book.getId();
					if (col == 2)
						Books[row][col] = book.getBookName();
					if (col == 3)
						Books[row][col] = MainFrame.instance.getName();
				}
				row++;
			}
		}else {
			btn_Return.setEnabled(false);
		}
		table = new JTable(Books, colNames);

		tablePane = new JScrollPane(table);

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 1, 30, 30));
		bottomPanel.add(btn_Return);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		addListener();

		splitPane.setBounds(0, 300, 1600, 750);
		splitPane.setRightComponent(bottomPanel);
		splitPane.setLeftComponent(tablePane);
		splitPane.setDividerLocation(300);
		splitPane.setContinuousLayout(true);
		splitPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		add(splitPane);

		setVisible(false);
	}

	public void addListener() {
		btn_Return.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = table.getSelectedRow();
				if (index == -1) {
					return;
				}
				Book book = books.get(index);
				BookAdder.setBookBorrowState(book, false);
				BookAdder.delBookBorrowRecord(MainFrame.instance.getReader(), book);
				JOptionPane.showMessageDialog(MainFrame.instance, "归还成功！");// 弹出小对话框
			}
		});
	}
}
