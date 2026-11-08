package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** Percorre uma copia das musicas existentes no momento de sua criacao. */
public class PlaylistIterator implements Iterator<String> {
    private final List<String> musicas;
    private int posicao;

    public PlaylistIterator(List<String> musicas) {
        this.musicas = new ArrayList<>(musicas);
    }

    @Override
    public boolean hasNext() {
        return posicao < musicas.size();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Nao ha mais musicas na playlist");
        }
        return musicas.get(posicao++);
    }
}
