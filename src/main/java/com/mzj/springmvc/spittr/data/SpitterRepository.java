package com.mzj.springmvc.spittr.data;

import com.mzj.springmvc.spittr.Spitter;

public interface SpitterRepository {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

}
