package edu.vt.kplin.shakesphere.client;

import edu.vt.kplin.shakesphere.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Shakesphere implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Tree actTree = new Tree();
		TreeItem scene1 = actTree.addItem("scene 1");
		TreeItem scene2 = actTree.addItem("Scene 2");
		scene1.addItem("woo");
		scene1.addItem("woo2");
		scene2.addItem("woo3");
		scene2.addItem("woo4");
		
		final StackPanel stackPanel = new DecoratedStackPanel();
		stackPanel.setSize("20pc", "40pc");
		stackPanel.add(actTree, "acts");
		
	    // Create a Horizontal Split Panel
	    HorizontalSplitPanel hSplit = new HorizontalSplitPanel();
	    //hSplit.ensureDebugId("cwHorizontalSplitPanel");
	    hSplit.setSize("80pc", "50pc");
	    hSplit.setSplitPosition("30%");

	    // Add some content
	    hSplit.setRightWidget(new HTML("asdfasdf asdfasfd"));
	    hSplit.setLeftWidget(stackPanel);

	    // Wrap the split panel in a decorator panel
	    DecoratorPanel decPanel = new DecoratorPanel();
	    decPanel.setWidget(hSplit);
	    
	    RootPanel.get().add(decPanel);
	    
	    AsyncCallback<PlayInfo> playInfoAsync = new AsyncCallback<PlayInfo>()
		{
			public void onSuccess(PlayInfo info)
			{
				stackPanel.clear();
				
				ActInfo[] actInfoArray = info.getActInfoArray();
				
				for (int i = 0; i < actInfoArray.length; i++)
				{
					ActInfo actInfo = actInfoArray[i];
					SceneInfo[] sceneInfoArray = actInfo.getSceneInfoArray();
					
					Tree scenesTree = new Tree();
					for (int j = 0; j < sceneInfoArray.length; j++)
					{
						scenesTree.addItem(sceneInfoArray[j].getTitle());
					}
					
					int currentAct = stackPanel.getSelectedIndex();
					
					stackPanel.add(scenesTree, actInfo.getTitle());
				}
			}
			
			public void onFailure(Throwable caught) {
			}
		};
		
		greetingService.getPlayInfo("dummy for now", playInfoAsync);
	}
		/*
		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}
				
				AsyncCallback<PlayInfo> playInfoAsync = new AsyncCallback<PlayInfo>()
				{
					public void onSuccess(PlayInfo info)
					{
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(info.getTitle() + " " + info.getSubtitle() + " " + info.getActInfoArray().length);
						dialogBox.center();
						closeButton.setFocus(true);
					}
					
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox
								.setText("Remote Procedure Call - Failure");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				};
				
				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.getPlayInfo("woo", playInfoAsync);
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}*/
}
