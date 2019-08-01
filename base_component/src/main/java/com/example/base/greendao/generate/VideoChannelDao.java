package com.example.base.greendao.generate;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.base.greendao.entity.VideoChannel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VIDEO_CHANNEL".
*/
public class VideoChannelDao extends AbstractDao<VideoChannel, Long> {

    public static final String TABLENAME = "VIDEO_CHANNEL";

    /**
     * Properties of entity VideoChannel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ChannelName = new Property(1, String.class, "channelName", false, "CHANNEL_NAME");
        public final static Property ChannelId = new Property(2, String.class, "channelId", false, "CHANNEL_ID");
    }


    public VideoChannelDao(DaoConfig config) {
        super(config);
    }
    
    public VideoChannelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VIDEO_CHANNEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CHANNEL_NAME\" TEXT NOT NULL UNIQUE ," + // 1: channelName
                "\"CHANNEL_ID\" TEXT NOT NULL UNIQUE );"); // 2: channelId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VIDEO_CHANNEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VideoChannel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getChannelName());
        stmt.bindString(3, entity.getChannelId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VideoChannel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getChannelName());
        stmt.bindString(3, entity.getChannelId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VideoChannel readEntity(Cursor cursor, int offset) {
        VideoChannel entity = new VideoChannel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // channelName
            cursor.getString(offset + 2) // channelId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VideoChannel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setChannelName(cursor.getString(offset + 1));
        entity.setChannelId(cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VideoChannel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VideoChannel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VideoChannel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}