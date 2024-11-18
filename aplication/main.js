function redirect(way, page) {
  document.location.href = way;

  var archive = URL('./acess.html')

  URL('./acess.html')
  if (page == 'login') {
    acess.style.display = 'flex'
    signin.style.display = 'none'
  } else if (page == 'signin') {
    acess.style.display = 'none'
    signin.style.display = 'flex'
  }
}