import com.intellij.ide.util.EditorHelper;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.project.Project;
import groovy.ui.SystemOutputInterceptor;
import org.jetbrains.annotations.NotNull;

/**
 * Menu action to replace a selection of characters with a fixed string.
 *
 * @see AnAction
 */
public class CodeCompletionAction extends AnAction {

    /**
     * Replaces the run of text selected by the primary caret with a fixed string.
     * @param e  Event related to this action
     */
    @Override
    public void actionPerformed(@NotNull final AnActionEvent e) {

        // Get all the required data from data keys
        // Editor and Project were verified in update(), so they are not null.
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document document = editor.getDocument();
        // Work off of the primary caret to get the selection info
        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
        LogicalPosition logicalPosition = primaryCaret.getLogicalPosition();
        int line = logicalPosition.line;
        System.out.println("line"+String.valueOf(line));
        String string = document.getText();
        String[] array = string.split("\n");
        for(int i = 0; i < array.length; i++)
        {
            System.out.println(i);
            System.out.println(array[i]);
        }

        int start = primaryCaret.getSelectionStart();
        int end = primaryCaret.getSelectionEnd();

        if(CodeMap.getInstance().containsKey(primaryCaret.getSelectedText()))
        {
            WriteCommandAction.runWriteCommandAction(project, () ->
                    document.replaceString(start, end, CodeMap.getInstance().get(primaryCaret.getSelectedText()))
            );
            return;
        }

        primaryCaret.removeSelection();
    }

    /**
     * Sets visibility and enables this action menu item if:
     *   A project is open,
     *   An editor is active,
     *   Some characters are selected
     * @param e  Event related to this action
     */
    @Override
    public void update(@NotNull final AnActionEvent e) {
        // Get required data keys
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        // Set visibility and enable only in case of existing project and editor and if a selection exists
        e.getPresentation().setEnabledAndVisible( project != null &&
                                                  editor != null
                                                  );
    }
}