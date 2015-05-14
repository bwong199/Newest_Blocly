package io.bloc.android.blocly.api.model;

/**
 * Created by benwong on 2015-05-14.
 */
public abstract class Model {

    private final long rowId;

    public Model(long rowId){
        this.rowId = rowId;
    }

    public long getRowId(){
        return rowId;
    }
}
