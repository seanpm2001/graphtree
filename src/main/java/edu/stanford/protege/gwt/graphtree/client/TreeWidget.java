package edu.stanford.protege.gwt.graphtree.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.event.shared.HandlerRegistration;
import edu.stanford.protege.gwt.graphtree.shared.Path;
import edu.stanford.protege.gwt.graphtree.shared.tree.RevealMode;
import edu.stanford.protege.gwt.graphtree.shared.tree.TreeNode;
import edu.stanford.protege.gwt.graphtree.shared.tree.TreeNodeId;
import edu.stanford.protege.gwt.graphtree.shared.tree.TreeNodeModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 21/01/2014
 */
@SuppressWarnings("unused")
public class TreeWidget<U extends Serializable, K> extends Composite implements HasAllMouseHandlers {

    private final TreePresenter<U, K> treePresenter;

    private final TreeView treeView;

    public TreeWidget(TreeView treeView, TreeNodeModel<U, K> model, TreeNodeRenderer<U> renderer) {
        SingleSelectionModel<TreeNode<U>> selectionModel = new SingleSelectionModel<>();
        treePresenter = new TreePresenter<>(treeView, selectionModel, renderer);
        treePresenter.setModel(model);
        this.treeView = treeView;
        initWidget(treeView.asWidget());
    }

    public TreeWidget(TreeNodeModel<U, K> model) {
        this(model, new TreeNodeRendererImpl<>());
    }

    public TreeWidget(TreeNodeModel<U, K> model, TreeNodeRenderer<U> renderer) {
        this(new TreeViewImpl(), model, renderer);
    }

    public void reload() {
        treePresenter.reload();

    }

    public void setModel(TreeNodeModel<U, K> model) {
        treePresenter.setModel(model);
    }

    public void setRootNodesExpanded() {
        treePresenter.setRootNodesExpanded();
    }

    public void setDropHandler(TreeNodeDropHandler<U> dropHandler) {
        treePresenter.setDropHandler(dropHandler);
    }

    public HandlerRegistration addSelectionChangeHandler(SelectionChangeEvent.Handler handler) {
        return treePresenter.addSelectionChangeHandler(handler);
    }

    public Path<TreeNodeId> getPathToRoot(TreeNodeId fromNode) {
        return treePresenter.getPathToRoot(fromNode);
    }

    public void clearSelection() {
        treePresenter.clearSelection();
    }

    public Set<TreeNode<U>> getSelectedSet() {
        return treePresenter.getSelectedSet();
    }

    public boolean isSelected(TreeNode<U> object) {
        return treePresenter.isSelected(object);
    }

    public void setSelected(TreeNode<U> object, boolean selected) {
        treePresenter.setSelected(object, selected);
    }

    public void setSelected(Path<K> keyPath, boolean selected, Runnable callback) {
        treePresenter.setSelected(keyPath, selected, callback);
    }

    public void setExpanded(Path<K> keyPath) {
        treePresenter.setExpanded(keyPath);
    }

    public void clearPruning() {
        treePresenter.clearPruning();
    }

    public void pruneToNodes(Collection<TreeNodeId> treeNodes) {
        treePresenter.pruneToNodes(treeNodes);
    }

    public void pruneToSelectedNodes() {
        List<TreeNodeId> selNodeIds = treePresenter.getSelectedSet().stream()
                                                   .map(TreeNode::getId)
                                                   .collect(toList());
        treePresenter.pruneToNodes(selNodeIds);
    }

    public void revealTreeNodesForUserObjectKey(K userObjectKey, RevealMode revealMode) {
        treePresenter.revealTreeNodesForUserObjectKey(userObjectKey, revealMode);
    }

    @Override
    public com.google.gwt.event.shared.HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
        return treeView.addMouseDownHandler(handler);
    }

    @Override
    public com.google.gwt.event.shared.HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
        return treeView.addMouseMoveHandler(handler);
    }

    @Override
    public com.google.gwt.event.shared.HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
        return treeView.addMouseOutHandler(handler);
    }

    @Override
    public com.google.gwt.event.shared.HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
        return treeView.addMouseOverHandler(handler);
    }

    @Override
    public com.google.gwt.event.shared.HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
        return treeView.addMouseUpHandler(handler);
    }

    @Override
    public com.google.gwt.event.shared.HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
        return treeView.addMouseWheelHandler(handler);
    }
}
