package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.NodeAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.INodeAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.INodeAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.INodeToViewAView;

public class NodeToViewAPresenterImpl implements INodeAPresenter {
    private INodeToViewAView mINodeToViewAView;
    private INodeAModel mINodeAModel;

    public NodeToViewAPresenterImpl(INodeToViewAView aINodeToViewAView) {
        mINodeToViewAView = aINodeToViewAView;
        mINodeAModel = new NodeAModelImpl();
    }
}
