//Consultas / Buscar documentos

//1. Obtener todos los documentos 

db.getCollection('movies').find({})

//2. Obtener documentos con writer igual a "Quentin Tarantino" 

db.getCollection('movies').find({writer: "Quentin Tarantino"})

//3. Obtener documentos con actors que incluyan a "Brad Pitt" 

db.getCollection('movies').find({actors: "Brad Pitt"})

//4. Obtener documentos con franchise igual a "The Hobbit" 

db.getCollection('movies').find({franchise: "The Hobbit"})

//5. Obtener todas las películas de los 90s. 

db.getCollection('movies').find({year: {$gte: 1990, $lte: 1999}})

//6. Obtener las películas estrenadas entre el año 2000 y 2010.

db.getCollection('movies').find({year: {$gt: 2000, $lt: 2010}})

// Actualizar Documentos

// 1. Agregar sinopsis a "The Hobbit: An Unexpected Journey" : "A reluctant hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home - and the gold within it - from the dragon Smaug." 

db.getCollection('movies').update({title: "The Hobbit: An Unexpected Journey"}, {$set:{"synopsis": "A reluctant hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home - and the gold within it - from the dragon Smaug."}})

// 2. Agregar sinopsis a "The Hobbit: The Desolation of Smaug" : "The dwarves, along with Bilbo Baggins and Gandalf the Grey, continue their quest to reclaim Erebor, their homeland, from Smaug. Bilbo Baggins is in possession of a mysterious and magical ring." 

db.getCollection('movies').update({title: "The Hobbit: The Desolation of Smaug"}, {$set:{synopsis: "The dwarves, along with Bilbo Baggins and Gandalf the Grey, continue their quest to reclaim Erebor, their homeland, from Smaug. Bilbo Baggins is in possession of a mysterious and magical ring."}})

// 3. Agregar una actor llamado "Samuel L. Jackson" a la película "Pulp Fiction"

db.getCollection('movies').update({title: "Pulp Fiction"}, {$push:{actors: "Samuel L. Jackson"}})

// Busqueda por Texto / Text Search

// 1. Encontrar las películas que en la sinopsis contengan la palabra "Bilbo" 

db.getCollection('movies').find({synopsis:{$regex: "Bilbo"} })

// 2. Encontrar las películas que en la sinopsis contengan la palabra "Gandalf" 

db.getCollection('movies').find({synopsis:{$regex: "Gandalf"} })

// 3. Encontrar las películas que en la sinopsis contengan la palabra "Bilbo" y no la palabra "Gandalf" 

db.getCollection('movies').find({synopsis: {$regex: "Bilbo", $not: {$regex: "Gandalf"}}})

// 4. Encontrar las películas que en la sinopsis contengan la palabra "dwarves" ó "hobbit" 

db.getCollection('movies').find({$or: [{synopsis: {$regex: "dwarves"}}, {synopsis: {$regex: "hobbit"}}]})

// 5. Encontrar las películas que en la sinopsis contengan la palabra "gold" y "dragon"

db.getCollection('movies').find({synopsis: {$regex: "gold", $regex:  "dragon"}})

// Eliminar Documentos

// 1. Eliminar la película "Pee Wee Herman's Big Adventure" 

db.getCollection('movies').remove({title: "Pee Wee Herman's Big Adventure"})

// 2. Eliminar la película "Avatar"

db.getCollection('movies').remove({title: "Avatar"})