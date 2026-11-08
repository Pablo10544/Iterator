package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/** Colecao de musicas que pode ser percorrida sem expor sua lista interna. */
public class Playlist implements Iterable<String> {
    private final List<String> musicas = new ArrayList<>();

    public void adicionar(String musica) {
        musicas.add(Objects.requireNonNull(musica, "A musica nao pode ser nula"));
    }

    @Override
    public Iterator<String> iterator() {
        return new PlaylistIterator(musicas);
    }
}
