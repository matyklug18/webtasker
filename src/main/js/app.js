const React = require('react');
const ReactDOM = require('react-dom');

import Login from './react/Login'
import Main from './react/Main'

import Cookies from 'js-cookie'

function redirect() {
	if(Cookies.get("logged") == "1")
	 	window.location.replace("page")
}
if(document.getElementById('react_index'))
	ReactDOM.render(
		<Login onload={redirect()}/>,
		document.getElementById('react_index')
	)

if(document.getElementById('react_page'))
	ReactDOM.render(
		<Main/>,
		document.getElementById('react_page')
	)