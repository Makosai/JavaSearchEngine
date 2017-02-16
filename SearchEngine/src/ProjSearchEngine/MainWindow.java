package ProjSearchEngine;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.CoolBar;

public class MainWindow {
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shlBlankSearchEngine = new Shell();
		shlBlankSearchEngine.setSize(657, 543);
		shlBlankSearchEngine.setText("Blank Search Engine");
		
		Menu menu = new Menu(shlBlankSearchEngine, SWT.BAR);
		shlBlankSearchEngine.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("File");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmAddFile = new MenuItem(menu_1, SWT.NONE);
		mntmAddFile.setText("Add File");
		
		MenuItem mntmRemoveFile = new MenuItem(menu_1, SWT.NONE);
		mntmRemoveFile.setText("Remove File");
		
		MenuItem mntmRefreshFiles = new MenuItem(menu, SWT.NONE);
		mntmRefreshFiles.setText("Refresh Files");
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("Help");
		
		text = new Text(shlBlankSearchEngine, SWT.BORDER);
		text.setBounds(154, 36, 360, 21);
		
		Label label = new Label(shlBlankSearchEngine, SWT.NONE);
		label.setBounds(48, 42, 55, 15);
		
		Button btnAnyTerm = new Button(shlBlankSearchEngine, SWT.RADIO);
		btnAnyTerm.setBounds(154, 75, 90, 16);
		btnAnyTerm.setText("Any Term");
		
		Button btnExactPhrase = new Button(shlBlankSearchEngine, SWT.RADIO);
		btnExactPhrase.setBounds(424, 75, 90, 16);
		btnExactPhrase.setText("Exact Phrase");
		
		Button btnAllTerms = new Button(shlBlankSearchEngine, SWT.RADIO);
		btnAllTerms.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnAllTerms.setBounds(299, 75, 90, 16);
		btnAllTerms.setText("All Terms");
		
		Button btnNewButton = new Button(shlBlankSearchEngine, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnNewButton.setBounds(289, 128, 90, 31);
		btnNewButton.setText("Search");

		shlBlankSearchEngine.open();
		shlBlankSearchEngine.layout();
		while (!shlBlankSearchEngine.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
