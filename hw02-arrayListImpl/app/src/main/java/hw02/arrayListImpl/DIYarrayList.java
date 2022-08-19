package hw02.arrayListImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DIYarrayList<T> implements List<T> {
    private final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public DIYarrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public DIYarrayList(int capacity) {
        this.size = 0;
        if (capacity > 0) {
            this.array = new Object[capacity];
        } else {
            this.array = new Object[DEFAULT_CAPACITY];
        }
    }

    public DIYarrayList(Collection<? super T> collection) {
        this.size = collection.size();
        if (collection.isEmpty()) {
            this.array = new Object[DEFAULT_CAPACITY];
        } else {
            this.array = collection.toArray();
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) this.array, 0, this.size, c);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T e) {
        if (this.size >= this.array.length) {
            this.array = Arrays.copyOf(this.array, this.array.length * 2);
        }

        this.array[size] = e;
        size++;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return (T) this.array[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        T temp = (T) this.array[index];
        this.array[index] = element;
        return temp;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        ListIterator<T> listIterator = new ListIteratorImpl(this);
        return listIterator;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        String temp = "";
        for (Object item : array) {
            if (item != null) {
                temp += item.toString() + " ";
            }
        }
        return temp;
    }

    private class ListIteratorImpl implements ListIterator<T> {
        private int cursor;
        private DIYarrayList<T> arrayList;
        private boolean isNextCalled, isPreviousCalled;

        public ListIteratorImpl(DIYarrayList<T> arrayList) {
            this.cursor = 0;
            this.arrayList = arrayList;
            this.isNextCalled = false;
            this.isPreviousCalled = false;
        }

        @Override
        public boolean hasNext() {
            if (this.cursor < arrayList.size()) {
                return true;
            }

            return false;
        }

        @Override
        public T next() {
            if (this.cursor >= this.arrayList.size()) {
                throw new NoSuchElementException();
            }

            this.isNextCalled = true;
            this.isPreviousCalled = false;
            return this.arrayList.get(this.cursor++);
        }

        @Override
        public boolean hasPrevious() {
            if (this.cursor > 0) {
                return true;
            }

            return false;
        }

        @Override
        public T previous() {
            if (this.cursor == 0) {
                throw new NoSuchElementException();
            }

            this.isPreviousCalled = true;
            this.isNextCalled = false;
            return this.arrayList.get(--this.cursor);
        }

        @Override
        public int nextIndex() {
            return this.cursor;
        }

        @Override
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override
        public void remove() {
            if (!isNextCalled && !isPreviousCalled) {
                throw new IllegalStateException();
            }

            Object[] temp = new Object[arrayList.size()];

            if (isNextCalled) {
                for (int i = 0; i < this.cursor - 1; ++i) {
                    temp[i] = arrayList.get(i);
                }
                for (int i = this.cursor; i < arrayList.size(); ++i) {
                    temp[i - 1] = arrayList.get(i);
                }
                cursor--;
            }
            if (isPreviousCalled) {
                for (int i = 0; i < this.cursor; ++i) {
                    temp[i] = arrayList.get(i);
                }
                for (int i = this.cursor + 1; i < arrayList.size(); ++i) {
                    temp[i - 1] = arrayList.get(i);
                }

            }

            this.arrayList.array = temp;
            this.isNextCalled = false;
            this.isPreviousCalled = false;
        }

        @Override
        public void set(Object e) {
            if (!isNextCalled && !isPreviousCalled) {
                throw new IllegalStateException();
            }

            if (isNextCalled) {
                this.arrayList.array[cursor - 1] = e;
            }
            
            if (isPreviousCalled) {
                this.arrayList.array[cursor] = e;
            }

        }

        @Override
        public void add(Object e) {
            Object[] temp = new Object[arrayList.size() + 1];

            for (int i = 0; i < this.cursor; ++i) {
                temp[i] = arrayList.get(i);
            }

            temp[cursor] = e;

            for (int i = this.cursor; i < arrayList.size(); ++i) {
                temp[i + 1] = arrayList.get(i);
            }

            this.arrayList.array = temp;
            this.cursor++;
            this.isNextCalled = false;
            this.isPreviousCalled = false;
        }

    }

}
