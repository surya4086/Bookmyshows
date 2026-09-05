async function fetchJson(url, options={}){
  const res=await fetch(url,{...options,headers:{"Content-Type":"application/json",...(options.headers||{})}});
  const text=await res.text(); let data=null; try{data=text?JSON.parse(text):null}catch{data=text}
  if(!res.ok) throw new Error(data?.message||data?.error||text||`HTTP ${res.status}`);
  return data;
}
async function tryGet(paths, suffix=""){
  let last;
  for(const path of paths){
    try{return await fetchJson(API_BASE+path+suffix)}catch(e){last=e}
  }
  throw last||new Error("API request failed");
}
async function tryPost(paths, body){
  let last;
  for(const path of paths){try{return await fetchJson(API_BASE+path,{method:"POST",body:JSON.stringify(body)})}catch(e){last=e}}
  throw last||new Error("POST failed");
}
async function tryPut(paths, body={}){
  let last;
  for(const path of paths){try{return await fetchJson(API_BASE+path,{method:"PUT",body:JSON.stringify(body)})}catch(e){last=e}}
  throw last||new Error("PUT failed");
}
async function getMovies(){
  return normalizeList(await tryGet(ENDPOINTS.movies));
}
async function searchMoviesByName(name){
  const q=encodeURIComponent(name);
  for(const path of ENDPOINTS.movieSearch){
    for(const key of ["title","name","query"]){
      try{return normalizeList(await fetchJson(API_BASE+path+`?${key}=${q}`))}catch(e){}
    }
  }
  const all=await getMovies();
  const n=name.toLowerCase();
  return all.filter(m=>(m.title||"").toLowerCase().includes(n));
}
function normalizeList(x){return Array.isArray(x)?x:(x?.content||x?.data||x?.movies||x?.theaters||x?.shows||[])}
async function getMovie(id){return tryGet(ENDPOINTS.movies,`/${id}`)}
async function getCities(){return normalizeList(await tryGet(ENDPOINTS.cities))}
async function getTheaters(){return normalizeList(await tryGet(ENDPOINTS.theaters))}
async function getShowsByMovie(movieId){
  const candidates=[`/movie/${movieId}`,`/movie/${movieId}/shows`,`/movie/${movieId}`];
  for(const p of candidates){try{return normalizeList(await tryGet(ENDPOINTS.shows,p))}catch(e){}}
  return [];
}
async function getShow(id){return tryGet(ENDPOINTS.shows,`/${id}`)}
async function getAvailableSeats(showId){
  const paths=[...ENDPOINTS.shows.map(p=>p+`/${showId}/available-seats`),...ENDPOINTS.bookings.map(p=>p+`/available-seats/${showId}`)];
  let last;for(const p of paths){try{return normalizeList(await fetchJson(API_BASE+p))}catch(e){last=e}}
  throw last||new Error("Available seats endpoint not found");
}
async function createBooking(body){return tryPost(ENDPOINTS.bookings,body)}
async function getUserBookings(id){
  const suffixes=[`/user/${id}`,`/user/${id}/bookings`,`?userId=${id}`];
  for(const s of suffixes){try{return normalizeList(await tryGet(ENDPOINTS.bookings,s))}catch(e){}}
  return [];
}
async function cancelBooking(id){
  const paths=ENDPOINTS.bookings.flatMap(p=>[p+`/${id}/cancel`,p+`/cancel/${id}`]);
  return tryPut(paths,{});
}
