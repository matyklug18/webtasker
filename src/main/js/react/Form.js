import React from 'react'

class Form extends React.Component {

    constructor(props) {
        super(props);
        this.state = {text: ''};
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({text: event.target.value});
    }

    render() {
        return <input type={this.props.type} placeholder={this.props.shadow} className='form' onChange={this.handleChange} />
    }
}

export default Form;    