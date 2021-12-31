import React, { useState } from "react";
import "../bridgelabzTemp.css";
import blz_logo from "../assets/blz_logo.png";

document.body.style.backgroundColor = "blue";

const Blz_homeTemp=()=>{
    const[inputName,setInputName]=useState("World");

    const inputNameFunc = (event) =>{
        const nameRegex = RegExp('^[A-Z]{1}[A-Za-z\\s]{2,}$');
        if(nameRegex.test(event.target.value)){
            setInputName(event.target.value);
        }
    }

    const goBridgelabz =()=>{
        window.open("https://www.bridgelabz.com/","_blank");
    }
   
    return(
    <div>
        <div className="row">
            <div className="col-sm-3"></div>
            <div className="col-sm-6 blz_orangebody">
                <h1>Hello {inputName} From BridgeLabz</h1>
                <img src={blz_logo} className="blz_logo" alt="Bridgelabz LOGO" className="blz_logo_img" onClick={goBridgelabz}></img>
                <input placeholder="Enter the Name" className="blz_input_name" onChange={inputNameFunc}/>
                <p>
                    At BridgeLabz,We're Techie community of
                </p>
                <ul>
                    <li>technologists</li>
                    <li>thinkers</li>
                    <li>builders</li>
                </ul>
                <p>
                    Working together to keep the tech Employability of Engineers alive and accessible,
                    so Tech companies worldwide can get contributors and creators for technology Solutions.
                    We Believe this act of collaboration accross an employability platform is essential
                    to individual growth and our collective future.
                </p>
                <p>
                    To know abot us,visit<a href="https://www.bridgelabz.com">Bridgelabz</a>
                    to learn even more bout our mission i.e.<strong>Employability to all.</strong>
                </p>
            </div>
            <div className="col-sm-3"></div>
        </div>

    </div>
    );
}
export default Blz_homeTemp;