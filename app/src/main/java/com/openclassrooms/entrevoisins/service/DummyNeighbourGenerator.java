package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline",
                    "https://i.pravatar.cc/150?u=a042581f4e29026704d",
                    "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 14",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                            "Pellentesque porttitor id sem ut blandit. Lorem ipsum dolor sit amet, " +
                            "consectetur adipiscing elit. Donec sed hendrerit ex. Cras a tempus risus. " +
                            "Aliquam egestas nulla non luctus blandit. Aenean dignissim massa ultrices volutpat bibendum. " +
                            "Integer semper diam et lorem iaculis pulvinar.",
                    true,
                    "www.facebook.fr/caroline"),

            new Neighbour(2,
                    "Jack",
                    "https://i.pravatar.cc/150?u=a042581f4e29026704e",
                    "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 00 55 90 14",
                    "Sed eget fringilla mauris, ac rutrum mauris. Curabitur finibus felis id justo porttitor, " +
                            "vitae hendrerit justo imperdiet. Donec tempus quam vulputate, elementum arcu a, molestie felis. " +
                            "Pellentesque eu risus luctus, tincidunt odio at, volutpat magna. Nam scelerisque vitae est vitae fermentum. " +
                            "Cras suscipit pretium ex, ut condimentum lorem sagittis sit amet. Praesent et gravida diam, at commodo lorem. " +
                            "Praesent tortor dui, fermentum vitae sollicitudin ut, elementum ut magna. Nulla volutpat tincidunt lectus, vel malesuada " +
                            "ante ultrices id.\n\n" +
                            "Ut scelerisque fringilla leo vitae dictum. Nunc suscipit urna tellus, a elementum eros accumsan vitae. " +
                            "Nunc lacinia turpis eu consectetur elementum. Cras scelerisque laoreet mauris ac pretium. Nam pellentesque " +
                            "ut orci ut scelerisque. Aliquam quis metus egestas, viverra neque vel, ornare velit. Nullam lobortis justo et ipsum " +
                            "sodales sodales. Etiam volutpat laoreet tellus, ultrices maximus nulla luctus ultricies. Praesent a dapibus arcu. In at magna " +
                            "in velit placerat vehicula nec in purus.",
                    true,
                    "www.facebook.fr/jack"),

            new Neighbour(3,
                    "Chloé",
                    "https://i.pravatar.cc/150?u=a042581f4e29026704f",
                    "Saint-Pierre-du-Mont ; 6km",
                    "+33 6 86 13 12 14",
                    "Sed ultricies suscipit semper. Fusce non blandit quam. ",
                    true,
                    "www.facebook.fr/chloe"),

            new Neighbour(4,
                    "Vincent",
                    "https://i.pravatar.cc/150?u=a042581f4e29026704a",
                    "Saint-Pierre-du-Mont ; 11km",
                    "+33 6 10 57 90 19",
                    "Etiam quis neque egestas, consectetur est quis, laoreet augue. " +
                            "Interdum et malesuada fames ac ante ipsum primis in faucibus. Morbi ipsum sem, " +
                            "commodo in nisi maximus, semper dignissim metus. Fusce eget nunc sollicitudin, dignissim " +
                            "tortor quis, consectetur mauris. ",
                    true,
                    "www.facebook.fr/vincent"),

            new Neighbour(5,
                    "Elodie",
                    "https://i.pravatar.cc/150?u=a042581f4e29026704b",
                    "Saint-Pierre-du-Mont ; 8km",
                    "+33 6 86 57 90 14",
                    "Fusce in ligula mi. Aliquam in sagittis tellus. Suspendisse tempor, velit et " +
                            "cursus facilisis, eros sapien sollicitudin mauris, et ultrices " +
                            "lectus sapien in neque. ",
                    true,
                    "www.facebook.fr/elodie"),

            new Neighbour(6,
                    "Sylvain",
                    "https://i.pravatar.cc/150?u=a042581f4e29026704c",
                    "Saint-Pierre-du-Mont ; 6km",
                    "+33 6 86 12 22 02",
                    "Integer pulvinar lacinia augue, a tempor ex semper eget. Nam lacinia " +
                            "lorem dapibus, pharetra ante in, auctor urna. Praesent sollicitudin metus sit " +
                            "amet nunc lobortis sodales. In eget ante congue, vestibulum leo ac, placerat leo. " +
                            "Nullam arcu purus, cursus a sollicitudin eu, lobortis vitae est. Sed sodales sit " +
                            "amet magna nec finibus. Nulla pellentesque, justo ut bibendum mollis, urna diam " +
                            "hendrerit dolor, non gravida urna quam id eros. ",
                    true,
                    "www.facebook.fr/sylvain"),

            new Neighbour(7,
                    "Laetitia",
                    "https://i.pravatar.cc/150?u=a042581f4e29026703d",
                    "Saint-Pierre-du-Mont ; 14km",
                    "+33 6 96 57 90 01",
                    "Vestibulum non leo vel mi ultrices pellentesque. ",
                    true,
                    "www.facebook.fr/laetitia"),

            new Neighbour(8,
                    "Dan",
                    "https://i.pravatar.cc/150?u=a042581f4e29026703b",
                    "Saint-Pierre-du-Mont ; 1km",
                    "+33 6 86 57 90 14",
                    "Cras non dapibus arcu. ",
                    true,
                    "www.facebook.fr/dan"),

            new Neighbour(9,
                    "Joseph",
                    "https://i.pravatar.cc/150?u=a042581f4e29026704d",
                    "Saint-Pierre-du-Mont ; 2km",
                    "+33 6 86 57 90 14",
                    "Donec neque odio, eleifend a luctus ac, tempor non libero. " +
                            "Nunc ullamcorper, erat non viverra feugiat, massa odio facilisis ligula, " +
                            "ut pharetra risus libero eget elit. Nulla malesuada, purus eu malesuada malesuada, est " +
                            "purus ullamcorper ipsum, quis congue velit mi sed lorem.",
                    true,
                    "www.facebook.fr/joseph"),

            new Neighbour(10,
                    "Emma",
                    "https://i.pravatar.cc/150?u=a042581f4e29026706d",
                    "Saint-Pierre-du-Mont ; 1km",
                    "+33 6 14 71 70 18",
                    "Aliquam erat volutpat. Mauris at massa nibh. Suspendisse auctor fermentum orci, " +
                            "nec auctor tortor. Ut eleifend euismod turpis vitae tempus. Sed a tincidunt ex. Etiam ut " +
                            "nibh ante. Fusce maximus lorem nibh, at sollicitudin ante ultrices vel. Praesent ac luctus ante, eu " +
                            "lacinia diam. Quisque malesuada vel arcu vitae euismod. Ut egestas mattis euismod.\n\n" +
                            "Vestibulum non leo vel mi ultrices pellentesque. Praesent ornare ligula " +
                            "non elit consectetur, pellentesque fringilla eros placerat. Etiam consequat dui " +
                            "eleifend justo iaculis, ac ullamcorper velit consectetur. Aliquam vitae elit ac ante ultricies aliquet " +
                            "vel at felis. Suspendisse ac placerat odio. Cras non dapibus arcu. Fusce pharetra nisi at orci rhoncus, " +
                            "vitae tristique nibh euismod.",
                    true,
                    "www.facebook.fr/emma"),

            new Neighbour(11,
                    "Patrick",
                    "https://i.pravatar.cc/150?u=a042581f4e29026702d",
                    "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 20 40 60 80",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce porttitor molestie " +
                    "libero, nec porta lectus posuere a. Curabitur in purus at enim commodo accumsan. Nullam molestie diam a massa finibus, in bibendum " +
                    "nulla ullamcorper. Nunc at enim enim. Maecenas quis posuere nisi. Mauris suscipit cursus velit id condimentum. Ut finibus turpis " +
                    "at nulla finibus sollicitudin. Nunc dolor mauris, blandit id ullamcorper vel, lacinia ac nisi. Vestibulum et sapien tempor, " +
                    "egestas lorem vitae, faucibus urna. Duis id odio massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, " +
                    "per inceptos himenaeos.\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce porttitor molestie  " +
                    "libero, nec porta lectus posuere a. Curabitur in purus at enim commodo accumsan. Nullam molestie diam a massa finibus, in bibendum " +
                    "nulla ullamcorper. Nunc at enim enim. Maecenas quis posuere nisi. Mauris suscipit cursus velit id condimentum. Ut finibus turpis " +
                    "at nulla finibus sollicitudin. Nunc dolor mauris, blandit id ullamcorper vel, lacinia ac nisi. Vestibulum et sapien tempor, " +
                    "egestas lorem vitae, faucibus urna. Duis id odio massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, " +
                    "per inceptos himenaeos.",
                    true,
                    "www.facebook.fr/patrick"),

            new Neighbour(12,
                    "Ludovic",
                    "https://i.pravatar.cc/150?u=a042581f3e39026702d",
                    "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 00 01 10 11",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce porttitor molestie " +
                    "libero, nec porta lectus posuere a. Curabitur in purus at enim commodo accumsan. Nullam molestie diam a massa finibus, in bibendum " +
                    "nulla ullamcorper. Nunc at enim enim. Maecenas quis posuere nisi. Mauris suscipit cursus velit id condimentum. Ut finibus turpis " +
                    "at nulla finibus sollicitudin. Nunc dolor mauris, blandit id ullamcorper vel, lacinia ac nisi. Vestibulum et sapien tempor, " +
                    "egestas lorem vitae, faucibus urna. Duis id odio massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, " +
                    "per inceptos himenaeos.\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce porttitor molestie  " +
                    "libero, nec porta lectus posuere a. Curabitur in purus at enim commodo accumsan. Nullam molestie diam a massa finibus, in bibendum " +
                    "nulla ullamcorper. Nunc at enim enim. Maecenas quis posuere nisi. Mauris suscipit cursus velit id condimentum. Ut finibus turpis " +
                    "at nulla finibus sollicitudin. Nunc dolor mauris, blandit id ullamcorper vel, lacinia ac nisi. Vestibulum et sapien tempor, " +
                    "egestas lorem vitae, faucibus urna. Duis id odio massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, " +
                    "per inceptos himenaeos.",
                    true,
                    "www.facebook.fr/ludovic")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
