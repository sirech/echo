use std::env;
use std::error::Error;
use std::collections::HashMap;

fn request(host: &str) -> Result<HashMap<String, String>, reqwest::Error> {
    reqwest::blocking::get(&format!("{}/fish/tur", host))?
        .json()
}

fn main() -> Result<(), Box<dyn Error>> {
    let host = env::var("HOST")?;
    let body = request(&host)?;

    println!("{:?}", body);

    Ok(())
}
