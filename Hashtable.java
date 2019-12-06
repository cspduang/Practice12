import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Hashtable {


    static final int DEFAULT_CAPACITY = 314527 + 100000;

    private int capacity;

    private int size;

    ArrayList<LinkedList<Entry>> hashtable;

    public Hashtable() {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.hashtable = new ArrayList<>(this.capacity);
        for (int i = 0; i < this.capacity; i++) {
            hashtable.add(null);
        }
    }

    public boolean containsKey(String key) {
        LinkedList<Entry> entries = hashtable.get(getHash(key) % capacity);
        if (entries == null || entries.size() == 0) {
            return false;
        }
        for (Entry entry : entries) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }


    public String get(String key) {
        LinkedList<Entry> entries = hashtable.get(getHash(key) % capacity);
        if (entries == null || entries.size() == 0) {
            return null;
        }
        for (Entry entry : entries) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void put(String key, String value) {
        int index = getHash(key) % capacity;
        LinkedList<Entry> entries = hashtable.get(index);
        if (entries == null) {
            entries = new LinkedList<>();
            entries.add(new Entry(key, value));
            size++;
        } else {
            boolean existKey = false;
            for (Entry entry : entries) {
                if (entry.getKey().equals(value)) {
                    existKey = true;
                    entry.setValue(value);
                    break;
                }
            }
            if (!existKey) {
                entries.add(new Entry(key, value));
                size++;
            }
        }
        hashtable.set(index, entries);
    }

    public String remove(String key) throws Exception {
        LinkedList<Entry> entries = hashtable.get(getHash(key) % capacity);
        if (entries == null || entries.size() == 0) {
            throw new Exception("The key does not exist in the hashtable");
        }
        Entry result = null;
        for (Entry entry : entries) {
            if (entry.getKey().equals(key)) {
                result = entry;
                break;
            }
        }
        if (null == result) {
            throw new Exception("The key does not exist in the hashtable");
        }
        entries.remove(result);
        return result.getValue();
    }

    private int getHash(String key) {
        int hashCode = key == null ? 0 : key.hashCode();
        if (hashCode < 0) {
            return -hashCode;
        }
        return hashCode;
    }


    private static class Entry {
        String key;
        String value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

}
