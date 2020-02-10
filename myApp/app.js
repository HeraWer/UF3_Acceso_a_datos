let express = require('express');
let app = express();

app.set('view engine', 'ejs');

app.get('/login', (req, res) => {
  res.render('loginform');
});

app.listen(3000, () => console.log('Example app listening on port 3000!'));


