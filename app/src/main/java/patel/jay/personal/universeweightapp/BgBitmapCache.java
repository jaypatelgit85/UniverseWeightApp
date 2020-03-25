package patel.jay.personal.universeweightapp;

import android.graphics.Bitmap;
import android.util.LruCache;


public class BgBitmapCache {
    private LruCache<Integer, Bitmap> mBackgroundsCache;

    private static BgBitmapCache instance;

    public static BgBitmapCache getInstance() {
        if (instance == null) {
            instance = new BgBitmapCache();
            instance.init();
        }
        return instance;
    }

    private void init() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 5;

        mBackgroundsCache = new LruCache<Integer, Bitmap>(cacheSize) {
            @Override
            protected void entryRemoved(boolean evicted, Integer key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
            }

            @Override
            protected int sizeOf(Integer key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToBgMemoryCache(Integer key, Bitmap bitmap) {
        if (getBitmapFromBgMemCache(key) == null) {
            mBackgroundsCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromBgMemCache(Integer key) {
        return mBackgroundsCache.get(key);
    }

}
